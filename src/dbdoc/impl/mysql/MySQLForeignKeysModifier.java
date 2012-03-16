package dbdoc.impl.mysql;

import java.util.ArrayList;
import java.util.List;

import dbdoc.importer.ReflectionTree;
import dbdoc.importer.TreeModifier;
import dbdoc.reflect.Column;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;

/**
 * @author jk
 */
public class MySQLForeignKeysModifier implements TreeModifier {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.importer.TreeModifier#filterTree(dbdoc.importer.
	 * ReflectionTree)
	 */
	@Override
	public void filterTree(ReflectionTree tree) {
		if (tree.root instanceof MySQLDatabase) {
			for (Schema schema : tree.root.getSchemas()) {
				filterSchema(schema);
			}
		}
	}

	private void filterSchema(Schema schema) {
		for (Table table : schema.getTables()) {
			for (Column column : table.getColumns()) {
				if (column.isReferencing()) {
					continue;
				}
				String colName = column.getName();
				int colNameLength = colName.length();
				for (Table foreign : schema.getTables()) {
					if (foreign == table) {
						continue;
					}
					String foreignName = foreign.getName();
					int foreignNameLength = foreignName.length();
					if (colNameLength < foreignNameLength + 2) {
						continue;
					}
					if (colName.substring(0, foreignNameLength).equals(
							foreignName)) {
						String foreignColName = colName
								.substring(foreignNameLength + 1);
						for (Column foreignCol : foreign.getColumns()) {
							if (foreignCol.getName().equals(foreignColName)) {
								addFKey(table, column, foreign, foreignCol);
							}
						}
					}
				}
			}
		}
	}

	private void addFKey(Table fromTable, Column fromColumn, Table toTable,
			Column toColumn) {
		String name = String.format("%s_%s__%s_%s_ifkey", fromTable.getName(),
				fromColumn.getName(), toTable.getName(), toColumn.getName());
		List<Column> columns = new ArrayList<Column>();
		columns.add(fromColumn);
		List<Column> foreignColumns = new ArrayList<Column>();
		foreignColumns.add(toColumn);

		MySQLForeignKey key = new MySQLForeignKey();
		key.setName(name);
		key.setColumns(columns);
		key.setForeignColumns(foreignColumns);

		((MySQLTable) fromTable).getConstraints().add(key);
		MySQLColumn column = (MySQLColumn) fromColumn;
		column.addFKeyOut(key);
		column = (MySQLColumn) toColumn;
		column.addFKeyIn(key);
		((MySQLTable) fromTable).addFKeyOut(key);
		((MySQLTable) toTable).addFKeyIn(key);
	}
}
