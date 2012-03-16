package dbdoc.reflect;

import java.util.List;

/**
 * Interface Database
 */
public interface Database {

	/**
	 * Returns the database name.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns a list of all schemas in this database. If the database doesn't
	 * support schemas, the list contains only one unnamed schema.
	 * 
	 * @return List<Schema>
	 */
	public List<Schema> getSchemas();

	/**
	 * Determines wether the database supports schemas.
	 * 
	 * @return boolean
	 */
	public boolean isSupportingSchemas();

	/**
	 * Returns the database comment or null if no comment was provided.
	 * 
	 * @return String
	 */
	public String getComment();
}
