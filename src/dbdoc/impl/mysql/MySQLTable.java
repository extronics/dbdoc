package dbdoc.impl.mysql;

import java.util.ArrayList;
import java.util.List;

import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;
import dbdoc.reflect.ForeignKey;
import dbdoc.reflect.Index;
import dbdoc.reflect.PrimaryKey;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;
import dbdoc.reflect.Trigger;

/**
 * @author jk
 * 
 */
public class MySQLTable implements Table {

	private Schema schema;
	private PrimaryKey primaryKey;
	private List<Constraint> constraints;
	private List<ForeignKey> foreignKeysOut;
	private List<ForeignKey> foreignKeysIn;
	private List<Index> indexes;
	private List<Column> columns;
	private List<Trigger> triggers;
	private String name;
	private String type;
	private String comment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getColumns()
	 */
	@Override
	public List<Column> getColumns() {
		return columns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getPrimaryKey()
	 */
	@Override
	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getConstraints()
	 */
	@Override
	public List<Constraint> getConstraints() {
		return constraints;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getForeignKeysOut()
	 */
	public List<ForeignKey> getForeignKeysOut() {
		return foreignKeysOut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getForeignKeysIn()
	 */
	public List<ForeignKey> getForeignKeysIn() {
		return foreignKeysIn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getIndexes()
	 */
	@Override
	public List<Index> getIndexes() {
		return indexes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getSchema()
	 */
	@Override
	public Schema getSchema() {
		return schema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Table#getTriggers()
	 */
	@Override
	public List<Trigger> getTriggers() {
		return triggers;
	}

	//
	// Internal getters/setters not part of the public api.
	//

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		int pos = comment.indexOf("InnoDB free: ");
		if (pos != -1) {
			if (pos == 0) {
				comment = null;
			} else {
				comment = comment.substring(0, pos - 2);
			}
		}

		this.comment = comment;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	public void setConstraints(List<Constraint> constraints) {
		for (Constraint c : constraints) {
			if (c instanceof PrimaryKey) {
				primaryKey = (PrimaryKey) c;
			}
		}
		this.constraints = constraints;
	}

	/**
	 * @param indexes
	 *            the indexes to set
	 */
	public void setIndexes(List<Index> indexes) {
		this.indexes = indexes;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/**
	 * @param triggers
	 *            the triggers to set
	 */
	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}

	/**
	 * Adds an incoming foreign key.
	 * 
	 * @param key
	 */
	public void addFKeyIn(ForeignKey key) {
		if (foreignKeysIn == null) {
			foreignKeysIn = new ArrayList<ForeignKey>();
		}
		if (!foreignKeysIn.contains(key)) {
			foreignKeysIn.add(key);
		}
	}

	/**
	 * Adds an outgoing foreign key.
	 * 
	 * @param key
	 */
	public void addFKeyOut(ForeignKey key) {
		if (foreignKeysOut == null) {
			foreignKeysOut = new ArrayList<ForeignKey>();
		}
		if (!foreignKeysOut.contains(key)) {
			foreignKeysOut.add(key);
		}
	}
}
