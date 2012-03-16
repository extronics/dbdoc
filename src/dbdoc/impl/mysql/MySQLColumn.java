package dbdoc.impl.mysql;

import java.util.ArrayList;
import java.util.List;

import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;
import dbdoc.reflect.ForeignKey;
import dbdoc.reflect.Index;
import dbdoc.reflect.PrimaryKey;
import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class MySQLColumn implements Column {

	private Table table;
	private String name;
	private int ordinalNumber;
	private String comment;
	private String dataType;
	private int dataLength;
	private boolean nullable;
	private boolean sequence;
	private String defaultValue;

	private List<Constraint> constraints;
	private List<Index> indexes;
	private PrimaryKey primaryKey;
	private List<ForeignKey> fkeysOut;
	private List<ForeignKey> fkeysIn;

	private List<Column> referenced;
	private List<Column> referencing;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getConstraints()
	 */
	@Override
	public List<Constraint> getConstraints() {
		return constraints;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getDataLength()
	 */
	@Override
	public int getDataLength() {
		return dataLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getDataType()
	 */
	@Override
	public String getDataType() {
		return dataType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getDefaultValue()
	 */
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getIndexes()
	 */
	@Override
	public List<Index> getIndexes() {
		return indexes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getOrdinalNumber()
	 */
	@Override
	public int getOrdinalNumber() {
		return ordinalNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getPrimaryKey()
	 */
	@Override
	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getReferencedColumns()
	 */
	@Override
	public List<Column> getReferencedColumns() {
		if (referenced == null && fkeysOut != null) {
			referenced = new ArrayList<Column>();
			for (ForeignKey key : fkeysOut) {
				referenced.addAll(key.getForeignColumns());
			}
		}
		return referenced;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getReferencingColumns()
	 */
	@Override
	public List<Column> getReferencingColumns() {
		if (referencing == null && fkeysIn != null) {
			referencing = new ArrayList<Column>();
			for (ForeignKey key : fkeysIn) {
				referencing.addAll(key.getColumns());
			}
		}
		return referencing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#getTable()
	 */
	@Override
	public Table getTable() {
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isConstrainedColumn()
	 */
	@Override
	public boolean isConstrainedColumn() {
		return getConstraints() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isIndexColumn()
	 */
	@Override
	public boolean isIndexColumn() {
		return getIndexes() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isNullable()
	 */
	@Override
	public boolean isNullable() {
		return nullable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isPrimaryKeyColumn()
	 */
	@Override
	public boolean isPrimaryKeyColumn() {
		return getPrimaryKey() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isReferenced()
	 */
	@Override
	public boolean isReferenced() {
		return fkeysIn != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isReferencing()
	 */
	@Override
	public boolean isReferencing() {
		return fkeysOut != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Column#isSequence()
	 */
	@Override
	public boolean isSequence() {
		return sequence;
	}

	//
	// Internal getters/setters not part of the public api.
	//

	/**
	 * Adds a constraint to the internal constraint list.
	 * 
	 * @param c
	 *            the constraint.
	 */
	public void addConstraint(Constraint c) {
		if (constraints == null) {
			constraints = new ArrayList<Constraint>();
		}
		if (c instanceof PrimaryKey) {
			primaryKey = (PrimaryKey) c;
		}
		constraints.add(c);
	}

	/**
	 * Adds an incoming foreign key.
	 * 
	 * @param key
	 */
	public void addFKeyIn(ForeignKey key) {
		if (fkeysIn == null) {
			fkeysIn = new ArrayList<ForeignKey>();
		}
		fkeysIn.add(key);
	}

	/**
	 * Adds an outgoing foreign key.
	 * 
	 * @param key
	 */
	public void addFKeyOut(ForeignKey key) {
		if (fkeysOut == null) {
			fkeysOut = new ArrayList<ForeignKey>();
		}
		fkeysOut.add(key);
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param ordinalNumber
	 *            the ordinalNumber to set
	 */
	public void setOrdinalNumber(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @param dataLength
	 *            the dataLength to set
	 */
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * @param nullable
	 *            the nullable to set
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public void setSequence(boolean sequence) {
		this.sequence = sequence;
	}

	/**
	 * @param index
	 *            the index to add
	 */
	public void addIndex(Index index) {
		if (indexes == null) {
			indexes = new ArrayList<Index>();
		}
		indexes.add(index);
	}
}
