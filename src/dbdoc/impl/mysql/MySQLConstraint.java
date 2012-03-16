package dbdoc.impl.mysql;

import java.util.List;

import dbdoc.impl.DBRef;
import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;

/**
 * @author jk
 * 
 */
public abstract class MySQLConstraint implements Constraint {

	private String name;
	private List<Column> columns;
	private List<DBRef> columnRefs;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Constraint#getColumns()
	 */
	@Override
	public List<Column> getColumns() {
		return columns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Constraint#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	//
	// Internal getters/setters not part of the public api.
	//

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the columnRefs
	 */
	public List<DBRef> getColumnRefs() {
		return columnRefs;
	}

	/**
	 * @param columnRefs
	 *            the columnRefs to set
	 */
	public void setColumnRefs(List<DBRef> columnRefs) {
		this.columnRefs = columnRefs;
	}

	/**
	 * @param column
	 *            the columns to set
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
