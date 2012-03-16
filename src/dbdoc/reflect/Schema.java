package dbdoc.reflect;

import java.util.List;

/**
 * Interface Schema
 */
public interface Schema {

	/**
	 * Returns the schema name or null, if the containing database doesn't
	 * support schemas.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns all stored procedures defined in this schema.
	 * 
	 * @return List<Procedure>
	 */
	public List<Procedure> getProcedures();

	/**
	 * Returns all tables defined in this schema.
	 * 
	 * @return List<Table>
	 */
	public List<Table> getTables();

	/**
	 * Returns the descriptive comment of this schema.
	 * 
	 * @return String
	 */
	public String getComment();

	/**
	 * Returns the database this schema belongs to.
	 * 
	 * @return Database
	 */
	public Database getDatabase();

}
