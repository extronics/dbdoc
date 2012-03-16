package dbdoc.impl.mysql;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import dbdoc.Arguments;
import dbdoc.InvalidArgumentException;
import dbdoc.MissingArgumentException;
import dbdoc.ProgressListener;
import dbdoc.impl.AbstractImporter;
import dbdoc.impl.DBRef;
import dbdoc.impl.DBRefIndex;
import dbdoc.impl.TextLoader;
import dbdoc.importer.ReflectionTree;
import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;
import dbdoc.reflect.Database;
import dbdoc.reflect.Index;
import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;
import dbdoc.reflect.Trigger;

/**
 * An importer for building reflection trees out of mysql databases. Supports
 * MySQL >= 5.0.
 * 
 * @author jk
 */
public class MySQLImporter extends AbstractImporter {

	private TextLoader loader;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.importer.Importer#run(dbdoc.Arguments)
	 */
	@Override
	public ReflectionTree run(Arguments args) {
		checkArguments(args);

		SqlMapClient client = getSqlMapClient(args);
		ReflectionTree tree = null;

		if (args.getString("text-dir") != null) {
			File textDir = new File(args.getString("text-dir"));
			loader = new TextLoader(textDir);
		}

		try {
			tree = getTree(client, args.getString("db", "mysql"));
		} catch (SQLException ex) {
			throw new InvalidArgumentException(
					"Invalid database configuration: %s", ex.getMessage());
		}

		return tree;
	}

	/**
	 * Builds the reflection tree for the specified database and applies the
	 * cross-linking between constraints/indexes and table columns.
	 * 
	 * @param client
	 *            The sql map client to use.
	 * @param name
	 *            The database.
	 * @return The reflection tree.
	 * @throws SQLException
	 */
	private ReflectionTree getTree(SqlMapClient client, String name)
			throws SQLException {
		notifyNextAction(ProgressListener.T_DB, name);

		ReflectionTree tree = getReflectionTree(client, name);
		if (tree == null) {
			return null;
		}

		Database db = tree.root;
		Schema schema = db.getSchemas().get(0);
		List<Table> tables = schema.getTables();
		for (Table t : tables) {
			List<Index> indexes = t.getIndexes();
			if (indexes != null) {
				for (Index i : indexes) {
					MySQLIndex index = (MySQLIndex) i;
					index.setColumns(buildColumnList(index.getColumnRefs(),
							tree.index, db.getName()));
					for (Column column : index.getColumns()) {
						((MySQLColumn) column).addIndex(index);
					}
				}
			}

			List<Constraint> constraints = t.getConstraints();
			if (constraints != null) {
				for (Constraint c : constraints) {
					MySQLConstraint constraint = (MySQLConstraint) c;
					constraint.setColumns(buildColumnList(constraint
							.getColumnRefs(), tree.index, db.getName()));

					for (Column column : constraint.getColumns()) {
						((MySQLColumn) column).addConstraint(constraint);
					}

					if (constraint instanceof MySQLForeignKey) {
						MySQLForeignKey fkey = (MySQLForeignKey) constraint;
						fkey.setForeignColumns(buildColumnList(fkey
								.getForeignColumnRefs(), tree.index, db
								.getName()));

						for (Column column : constraint.getColumns()) {
							((MySQLColumn) column).addFKeyOut(fkey);
							((MySQLTable) column.getTable()).addFKeyOut(fkey);
						}
						for (Column column : fkey.getForeignColumns()) {
							((MySQLColumn) column).addFKeyIn(fkey);
							((MySQLTable) column.getTable()).addFKeyIn(fkey);
						}
					}
				}
			}
		}

		return tree;
	}

	/**
	 * Builds the initial object tree representing the database and fills the
	 * table and column index.
	 * 
	 * @param client
	 *            The sql map client to use.
	 * @param name
	 *            The database.
	 * @return The object tree.
	 * @throws SQLException
	 *             if an database error occurs.
	 */
	@SuppressWarnings("unchecked")
	private ReflectionTree getReflectionTree(SqlMapClient client, String name)
			throws SQLException {
		ReflectionTree tree = new ReflectionTree();
		MySQLDatabase db = (MySQLDatabase) client.queryForObject(
				"Reflect.getDatabase", name);

		if (db == null) {
			return null;
		}

		DBRefIndex index = new DBRefIndex();
		tree.root = db;
		tree.index = index;
		addAdditionalComment(db);

		MySQLSchema schema = (MySQLSchema) client.queryForObject(
				"Reflect.getSchemas", db.getName());
		schema.setDatabase(db);
		List<Schema> schemas = new ArrayList<Schema>();
		schemas.add(schema);
		db.setSchemas(schemas);
		index.add(schema);

		List<Table> tables = (List<Table>) client.queryForList(
				"Reflect.getTables", db.getName());
		for (Table t : tables) {
			notifyNextAction(ProgressListener.T_TABLE, t.getName());
			MySQLTable table = (MySQLTable) t;
			table.setSchema(schema);
			index.add(table);
			addAdditionalComment(table);

			Map<String, String> query = new HashMap<String, String>();
			query.put("schema", db.getName());
			query.put("table", table.getName());

			List<Column> columns = (List<Column>) client.queryForList(
					"Reflect.getColumns", query);
			for (Column c : columns) {
				notifyNextAction(ProgressListener.T_COLUMN, c.getName());
				MySQLColumn column = (MySQLColumn) c;
				column.setTable(table);
				index.add(column);
				addAdditionalComment(column);
			}
			table.setColumns(columns);

			List<Constraint> constraints = (List<Constraint>) client
					.queryForList("Reflect.getConstraints", query);
			table.setConstraints(constraints);

			List<Index> indexes = (List<Index>) client.queryForList(
					"Reflect.getIndexes", query);
			table.setIndexes(indexes);

			List<Trigger> triggers = (List<Trigger>) client.queryForList(
					"Reflect.getTriggers", query);
			table.setTriggers(triggers);
		}

		schema.setTables(tables);

		List<Procedure> procedures = (List<Procedure>) client.queryForList(
				"Reflect.getProcedures", db.getName());
		for (Procedure p : procedures) {
			MySQLProcedure procedure = (MySQLProcedure) p;
			procedure.setSchema(schema);
			addAdditionalComment(procedure);
		}
		schema.setProcedures(procedures);

		return tree;
	}

	/**
	 * Adds an additional text to the database comment if this feature is
	 * enabled.
	 * 
	 * @param db
	 */
	private void addAdditionalComment(MySQLDatabase db) {
		if (loader == null) {
			return;
		}
		String comment = loader.getDatabaseComment();
		if (comment != null) {
			if (db.getComment() != null) {
				db.setComment(db.getComment() + comment);
			} else {
				db.setComment(comment);
			}
		}
	}

	/**
	 * Adds an additional text to the table comment if this feature is enabled.
	 * 
	 * @param table
	 */
	private void addAdditionalComment(MySQLTable table) {
		if (loader == null) {
			return;
		}
		String comment = loader.getTableComment(table);
		if (comment != null) {
			if (table.getComment() != null) {
				table.setComment(table.getComment() + comment);
			} else {
				table.setComment(comment);
			}
		}
	}

	/**
	 * Adds an additional text to the column comment if this feature is enabled.
	 * 
	 * @param column
	 */
	private void addAdditionalComment(MySQLColumn column) {
		if (loader == null) {
			return;
		}
		String comment = loader.getColumnComment(column);
		if (comment != null) {
			if (column.getComment() != null) {
				column.setComment(column.getComment() + comment);
			} else {
				column.setComment(comment);
			}
		}
	}

	/**
	 * Adds an additional text to the procedure comment if this feature is
	 * enabled.
	 * 
	 * @param proc
	 */
	private void addAdditionalComment(MySQLProcedure proc) {
		if (loader == null) {
			return;
		}
		String comment = loader.getProcedureComment(proc);
		if (comment != null) {
			if (proc.getComment() != null) {
				proc.setComment(proc.getComment() + comment);
			} else {
				proc.setComment(comment);
			}
		}
	}

	/**
	 * Builds a column list by using the specified index and list of references.
	 * 
	 * @param refs
	 *            A list of column references.
	 * @param index
	 *            The database index.
	 * @param db
	 *            The database name.
	 * @return
	 */
	private List<Column> buildColumnList(List<DBRef> refs, DBRefIndex index,
			String db) {
		List<Column> columns = new ArrayList<Column>();
		for (DBRef ref : refs) {
			Column c = index.getColumn(db, ref.getSchema(), ref.getTable(), ref
					.getColumn());
			if (c != null) {
				columns.add(c);
			}
		}
		return columns;
	}

	/**
	 * Builds an SqlMapClient from the specified set of command line arguments.
	 * 
	 * @param args
	 * @return
	 */
	private SqlMapClient getSqlMapClient(Arguments args) {
		String driver = "com.mysql.jdbc.Driver";
		String host = args.getString("host", "mysql");
		String port = args.getString("port", "mysql");
		String db = args.getString("db", "mysql");
		String user = args.getString("username", "mysql");
		String pass = args.getString("password", "mysql");
		String sql = "dbdoc/impl/mysql/mysql.map.xml";

		if (port == null) {
			port = "3306";
		}
		if (user == null) {
			user = "root";
		}
		if (pass == null) {
			pass = "";
		}

		String url = String.format("jdbc:mysql://%s:%s/%s", host, port, db);
		return buildSqlMapClient(driver, url, user, pass, sql);
	}

	/**
	 * Ensures that all required arguments are provided.
	 * 
	 * @param args
	 */
	private void checkArguments(Arguments args) {
		if (args.getString("host", "mysql") == null) {
			throw new MissingArgumentException(
					"Must specify the database host.");
		}
		if (args.getString("db", "mysql") == null) {
			throw new MissingArgumentException(
					"Must specify the database name.");
		}
	}
}
