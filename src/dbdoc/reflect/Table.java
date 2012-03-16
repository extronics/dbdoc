package dbdoc.reflect;

import java.util.List;

/**
 * Interface Table
 */
public interface Table {

	/**
	 * Returns the table's name.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns all columns in this table.
	 * 
	 * @return List<Column>
	 */
	public List<Column> getColumns();

	/**
	 * Returns all triggers defined on this table.
	 * 
	 * @return List<Trigger>
	 */
	public List<Trigger> getTriggers();

	/**
	 * Returns the tables primary key or <tt>null</tt> if the table has none.
	 * 
	 * @return PrimaryKey
	 */
	public PrimaryKey getPrimaryKey();

	/**
	 * Returns all table constrains.
	 * 
	 * @return List<Constraint>
	 */
	public List<Constraint> getConstraints();

	/**
	 * Returns a list of all outgoing foreign keys.
	 * 
	 * @return List<ForeignKey>
	 */
	public List<ForeignKey> getForeignKeysOut();

	/**
	 * Returns a list of all incomging foreign keys.
	 * 
	 * @return List<ForeignKey>
	 */
	public List<ForeignKey> getForeignKeysIn();

	/**
	 * Returns all indexes defined on this table.
	 * 
	 * @return List<Index>
	 */
	public List<Index> getIndexes();

	/**
	 * Returns the table's comment.
	 * 
	 * @return String
	 */
	public String getComment();

	/**
	 * Returns the schema this table belongs to.
	 * 
	 * @return reflect.Schema
	 */
	public Schema getSchema();
}
