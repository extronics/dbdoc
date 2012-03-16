package dbdoc.reflect;

import java.util.List;

/**
 * Interface Constraint Base interface for table constraints (unique, primary
 * keys, foreign keys, ...)
 */
public interface Constraint {

	/**
	 * Returns the constraint's name.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns the columns in this constraint.
	 * 
	 * @return List<Column>
	 */
	public List<Column> getColumns();
}
