package dbdoc.reflect;

import java.util.List;

/**
 * Interface Column
 */
public interface Column {

	/**
	 * Returns the column name.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns the name of the column's data type.
	 * 
	 * @return String
	 */
	public String getDataType();

	/**
	 * Returns the maximum data length of the column.
	 * 
	 * For character columns, this returns the maximum storable amount of
	 * characters.
	 * 
	 * For numeric columns, this returns the numeric precision.
	 * 
	 * @return int
	 */
	public int getDataLength();

	/**
	 * Determines wether the column can store null values.
	 * 
	 * @return boolean
	 */
	public boolean isNullable();

	/**
	 * Returns the column's default value or null if the column has no default
	 * value.
	 * 
	 * @return String
	 */
	public String getDefaultValue();

	/**
	 * Returns the column's comment.
	 * 
	 * @return String
	 */
	public String getComment();

	/**
	 * Determines wether the column is an auto generated column, known as
	 * sequence, identity or auto increment.
	 * 
	 * @return boolean
	 */
	public boolean isSequence();

	/**
	 * Returns the column's ordinal number within it's table.
	 * 
	 * @return int
	 */
	public int getOrdinalNumber();

	/**
	 * Returns true if the column is part of a table index.
	 * 
	 * @return boolean
	 */
	public boolean isIndexColumn();

	/**
	 * Returns true if the column is part of a table constraint.
	 * 
	 * @return boolean
	 */
	public boolean isConstrainedColumn();

	/**
	 * Returns true if the column is part of the table's primary key.
	 * 
	 * @return boolean
	 */
	public boolean isPrimaryKeyColumn();

	/**
	 * Returns a list of all indexes this column is part of.
	 * 
	 * @return List<Index>
	 */
	public List<Index> getIndexes();

	/**
	 * Returns a list of all table constraints this column is part of.
	 * 
	 * @return List<Constraint>
	 */
	public List<Constraint> getConstraints();

	/**
	 * Returns the primary key this column is part of.
	 * 
	 * @return PrimaryKey
	 */
	public PrimaryKey getPrimaryKey();

	/**
	 * Returns true if this column is referenced by another column
	 * 
	 * @return boolean
	 */
	public boolean isReferenced();

	/**
	 * @return Table
	 */
	public Table getTable();

	/**
	 * Returns a list of all columns referencing this column.
	 * 
	 * @return List<Column>
	 */
	public List<Column> getReferencingColumns();

	/**
	 * Returns true if this column is referencing other columns.
	 * 
	 * @return boolean
	 */
	public boolean isReferencing();

	/**
	 * Returns a list of all referenced columns.
	 * 
	 * @return List<Column>
	 */
	public List<Column> getReferencedColumns();
}
