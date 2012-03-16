package dbdoc.reflect;

/**
 * Interface Procedure
 */
public interface Procedure {

	/**
	 * Returnes the procedure's name.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns the procedure's comment.
	 * 
	 * @return String
	 */
	public String getComment();

	/**
	 * Returns the procedure's schema.
	 * 
	 * @return Schema
	 */
	public Schema getSchema();

	/**
	 * Returns the procedures body.
	 * 
	 * @return String
	 */
	public String getBody();
}
