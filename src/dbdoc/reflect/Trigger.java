package dbdoc.reflect;

/**
 * Interface Trigger
 */
public interface Trigger {

	/**
	 * Returns true if the trigger is executed before the triggering statement.
	 * 
	 * @return boolean
	 */
	public boolean isBefore();

	/**
	 * Returns true if the trigger is executed after the triggering statement.
	 * 
	 * @return boolean
	 */
	public boolean isAfter();

	/**
	 * Returns true if this trigger is executed by delete statements.
	 * 
	 * @return boolean
	 */
	public boolean isOnDelete();

	/**
	 * Returns true if this trigger is executed by update statements.
	 * 
	 * @return boolean
	 */
	public boolean isOnUpdate();

	/**
	 * Returns true if this trigger is executed by insert statements.
	 * 
	 * @return boolean
	 */
	public boolean isOnInsert();

	/**
	 * Returns the statement type which executes the trigger.
	 * 
	 * @return StatementType
	 */
	public StatementType getStatementType();

	/**
	 * Returns the timing of this trigger.
	 * 
	 * @return Timing
	 */
	public Timing getTiming();

	/**
	 * Returns the name of this trigger.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns the body which contains the triggers code.
	 * 
	 * @return
	 */
	public String getBody();
}
