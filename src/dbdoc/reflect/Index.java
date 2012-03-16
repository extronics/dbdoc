package dbdoc.reflect;

import java.util.List;

/**
 * Interface Index
 */
public interface Index {

	/**
	 * Returns the name of the index.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Returns the columns in this index.
	 * 
	 * @return List<Column>
	 */
	public List<Column> getColumns();
}
