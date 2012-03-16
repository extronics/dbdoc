package dbdoc;

/**
 * Interface ProgressListener
 */
public interface ProgressListener {
	public static final String T_DB         = "database";
	public static final String T_SCHEMA     = "schema";
	public static final String T_TABLE      = "table";
	public static final String T_COLUMN     = "column";
	public static final String T_CONSTRAINT = "constraint";
	public static final String T_TRIGGER    = "trigger";
	public static final String T_PROCEDURE  = "procedure";
	public static final String T_INDEX      = "index";

	/**
	 * Notifies the listener about the next action processed by the observable.
	 * 
	 * @param objType
	 *            The type of the object processed next.
	 * @param objName
	 *            Name of the object processed next.
	 */
	public void nextAction(String objType, String objName);
}
