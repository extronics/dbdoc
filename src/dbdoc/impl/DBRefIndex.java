package dbdoc.impl;

import java.util.HashMap;
import java.util.Map;

import dbdoc.reflect.Column;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class DBRefIndex {
	private Map<DBRef, Object> index;
	private DBRef lookupKey;

	public DBRefIndex() {
		index = new HashMap<DBRef, Object>();
		lookupKey = new DBRef();
	}

	public void add(Schema schema) {
		String db = schema.getDatabase().getName();
		String name = schema.getName();
		DBRef key = new DBRef(db, name, null, null);
		index.put(key, schema);
	}

	public void add(Table table) {
		String db = table.getSchema().getDatabase().getName();
		String schema = table.getSchema().getName();
		String name = table.getName();
		DBRef key = new DBRef(db, schema, name, null);
		index.put(key, table);
	}

	public void add(Column column) {
		String db = column.getTable().getSchema().getDatabase().getName();
		String schema = column.getTable().getSchema().getName();
		String table = column.getTable().getName();
		String name = column.getName();
		DBRef key = new DBRef(db, schema, table, name);
		index.put(key, column);
	}

	public Schema getSchema(String db, String schema) {
		lookupKey.setDatabase(db);
		lookupKey.setSchema(schema);
		lookupKey.setTable(null);
		lookupKey.setColumn(null);
		
		Object result = index.get(lookupKey);
		if (result != null && !(result instanceof Schema)) {
			throw new IllegalStateException("Expected object of type Schema, "
					+ result + " returned");
		}
		return (Schema) result;
	}

	public Table getTable(String db, String schema, String table) {
		lookupKey.setDatabase(db);
		lookupKey.setSchema(schema);
		lookupKey.setTable(table);
		lookupKey.setColumn(null);

		Object result = index.get(lookupKey);
		if (result != null && !(result instanceof Table)) {
			throw new IllegalStateException("Expected object of type Table, "
					+ result + " returned");
		}
		return (Table) result;
	}

	public Column getColumn(String db, String schema, String table,
			String column) {
		lookupKey.setDatabase(db);
		lookupKey.setSchema(schema);
		lookupKey.setTable(table);
		lookupKey.setColumn(column);

		Object result = index.get(lookupKey);
		if (result != null && !(result instanceof Column)) {
			throw new IllegalStateException("Expected object of type Column, "
					+ result + " returned");
		}
		return (Column) result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return index.toString();
	}
}
