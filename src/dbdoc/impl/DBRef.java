package dbdoc.impl;

/**
 * @author jk
 */
public class DBRef {
	private String database;
	private String schema;
	private String table;
	private String column;

	public DBRef() {
		super();
	}

	public DBRef(String database, String schema, String table, String column) {
		this.database = database;
		this.schema = schema;
		this.table = table;
		this.column = column;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return database + "." + schema + "." + table + "." + column + "@"
				+ hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int hashCode = database.hashCode();

		if (schema != null) {
			hashCode ^= schema.hashCode();
		}
		if (table != null) {
			hashCode ^= table.hashCode();
		}
		if (column != null) {
			hashCode ^= column.hashCode();
		}

		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof DBRef) {
			return hashCode() == ((DBRef) o).hashCode();
		}
		return false;
	}
}
