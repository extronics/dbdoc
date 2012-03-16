package dbdoc.impl.mysql;

import java.util.List;

import dbdoc.impl.DBRef;
import dbdoc.reflect.Column;
import dbdoc.reflect.ForeignKey;
import dbdoc.reflect.Restriction;

/**
 * @author jk
 * 
 */
public class MySQLForeignKey extends MySQLConstraint implements ForeignKey {

	private List<DBRef> foreignColumnRefs;
	private List<Column> foreignColumns;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.ForeignKey#getForeignColumns()
	 */
	@Override
	public List<Column> getForeignColumns() {
		return foreignColumns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.ForeignKey#getOnDeleteAction()
	 */
	@Override
	public Restriction getOnDeleteAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.ForeignKey#getOnUpdateAction()
	 */
	@Override
	public Restriction getOnUpdateAction() {
		// TODO Auto-generated method stub
		return null;
	}

	//
	// Internal getters/setters not part of the public api.
	//

	/**
	 * @param foreignColumns
	 *            the foreignColumns to set
	 */
	public void setForeignColumns(List<Column> foreignColumns) {
		this.foreignColumns = foreignColumns;
	}

	/**
	 * @param foreignColumnRefs
	 *            the foreignColumnRefs to set
	 */
	public void setForeignColumnRefs(List<DBRef> foreignColumnRefs) {
		this.foreignColumnRefs = foreignColumnRefs;
	}

	/**
	 * @return the foreignColumnRefs
	 */
	public List<DBRef> getForeignColumnRefs() {
		return foreignColumnRefs;
	}
}
