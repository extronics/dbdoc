package dbdoc.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import dbdoc.TestCase;
import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;
import dbdoc.reflect.Database;
import dbdoc.reflect.ForeignKey;
import dbdoc.reflect.Index;
import dbdoc.reflect.PrimaryKey;
import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;
import dbdoc.reflect.Trigger;

/**
 * @author jk
 * 
 */
public class TestDBRefIndex extends TestCase {
	@Test
	public void testConstruct() {
		new DBRefIndex();
	}

	@Test
	public void testAddSchema() {
		DBRefIndex index = new DBRefIndex();
		Schema schema = new SchemaStub(null, new DatabaseStub("testdb"));
		index.add(schema);

		assertNotNull(index.getSchema("testdb", null));
		assertNull(index.getSchema("testdb", "foo"));
		assertNull(index.getTable("testdb", null, "footable"));
		assertNull(index.getColumn("testdb", null, "footable", "foocolumn"));
	}

	@Test
	public void testAddTable() {
		DBRefIndex index = new DBRefIndex();
		Table table = new TableStub("testtab", new SchemaStub(null,
				new DatabaseStub("testdb")));
		index.add(table);

		assertNotNull(index.getTable("testdb", null, "testtab"));
		assertNull(index.getSchema("testdb", "foo"));
		assertNull(index.getTable("testdb", null, "footable"));
		assertNull(index.getColumn("testdb", null, "footable", "foocolumn"));
	}

	@Test
	public void testAddColumn() {
		DBRefIndex index = new DBRefIndex();
		Column col = new ColumnStub("testcol", new TableStub("testtab",
				new SchemaStub(null, new DatabaseStub("testdb"))));
		index.add(col);

		assertNotNull(index.getColumn("testdb", null, "testtab", "testcol"));
		assertNull(index.getSchema("testdb", "foo"));
		assertNull(index.getTable("testdb", null, "footable"));
		assertNull(index.getColumn("testdb", null, "footable", "foocolumn"));
	}

	//
	//
	//

	public static class DatabaseStub implements Database {
		private String name;

		public DatabaseStub(String name) {
			this.name = name;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public List<Schema> getSchemas() {
			return null;
		}

		@Override
		public boolean isSupportingSchemas() {
			return false;
		}
	}

	public static class SchemaStub implements Schema {
		private String name;
		private Database database;

		public SchemaStub(String name, Database database) {
			this.name = name;
			this.database = database;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public Database getDatabase() {
			return database;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public List<Procedure> getProcedures() {
			return null;
		}

		@Override
		public List<Table> getTables() {
			return null;
		}
	}

	public static class TableStub implements Table {
		private String name;
		private Schema schema;

		public TableStub(String name, Schema schema) {
			this.name = name;
			this.schema = schema;
		}

		@Override
		public List<Column> getColumns() {
			return null;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public List<Constraint> getConstraints() {
			return null;
		}

		@Override
		public List<Index> getIndexes() {
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Schema getSchema() {
			return schema;
		}

		@Override
		public List<Trigger> getTriggers() {
			return null;
		}

		@Override
		public PrimaryKey getPrimaryKey() {
			return null;
		}

		@Override
		public List<ForeignKey> getForeignKeysIn() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ForeignKey> getForeignKeysOut() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public static class ColumnStub implements Column {
		private String name;
		private Table table;

		public ColumnStub(String name, Table table) {
			this.name = name;
			this.table = table;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public List<Constraint> getConstraints() {
			return null;
		}

		@Override
		public int getDataLength() {
			return 0;
		}

		@Override
		public String getDataType() {
			return null;
		}

		@Override
		public String getDefaultValue() {
			return null;
		}

		@Override
		public List<Index> getIndexes() {
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getOrdinalNumber() {
			return 0;
		}

		@Override
		public PrimaryKey getPrimaryKey() {
			return null;
		}

		@Override
		public List<Column> getReferencedColumns() {
			return null;
		}

		@Override
		public List<Column> getReferencingColumns() {
			return null;
		}

		@Override
		public Table getTable() {
			return table;
		}

		@Override
		public boolean isConstrainedColumn() {
			return false;
		}

		@Override
		public boolean isIndexColumn() {
			return false;
		}

		@Override
		public boolean isNullable() {
			return false;
		}

		@Override
		public boolean isPrimaryKeyColumn() {
			return false;
		}

		@Override
		public boolean isReferenced() {
			return false;
		}

		@Override
		public boolean isReferencing() {
			return false;
		}

		@Override
		public boolean isSequence() {
			return false;
		}
	}
}
