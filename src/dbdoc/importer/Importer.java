package dbdoc.importer;

import dbdoc.Arguments;
import dbdoc.ProgressListener;

/**
 * Interface Importer
 */
public interface Importer {

	/**
	 * Runs the database importer.
	 * 
	 * @return ReflectionTree
	 * @param args
	 *            The importer arguments, like database connection parameters.
	 */
	public ReflectionTree run(Arguments args);

	/**
	 * Adds a listener which is notified on each import step.
	 * 
	 * @param listener
	 *            The listener.
	 */
	public void addListener(ProgressListener listener);
}
