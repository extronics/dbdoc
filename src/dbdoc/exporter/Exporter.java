package dbdoc.exporter;

import dbdoc.Arguments;
import dbdoc.ProgressListener;
import dbdoc.reflect.Database;

/**
 * Interface Exporter
 */
public interface Exporter {

	/**
	 * Starts the documentation export of the specified database.
	 * 
	 * @param args
	 * @param db
	 */
	public void run(Arguments args, Database db);

	/**
	 * Adds a new progress listener to the exporter.
	 * 
	 * @param listener
	 *            The listener to add.
	 */
	public void addListener(ProgressListener listener);

}