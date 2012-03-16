package dbdoc.reflect;

import java.util.List;

/**
 * Interface ForeignKey
 */
public interface ForeignKey extends Constraint {

	/**
	 * Returns the columns referenced by this foreign key.
	 * 
	 * @return List<Column>
	 */
	public List<Column> getForeignColumns();

	/**
	 * Returns the action which is executed if a referenced column is updated.
	 * 
	 * @return Restriction
	 */
	public Restriction getOnUpdateAction();

	/**
	 * Returns the action which is executed if a referenced column is deleted.
	 * 
	 * @return Restriction
	 */
	public Restriction getOnDeleteAction();
}
