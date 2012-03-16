package dbdoc.impl;

import java.util.HashSet;
import java.util.Set;

import dbdoc.ProgressListener;
import dbdoc.exporter.Exporter;

/**
 * @author jk
 * 
 */
public abstract class AbstractExporter implements Exporter {

	private Set<ProgressListener> listeners;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.exporter.Exporter#addListener(dbdoc.ProgressListener)
	 */
	@Override
	public void addListener(ProgressListener listener) {
		if (listeners == null) {
			listeners = new HashSet<ProgressListener>();
		}
		listeners.add(listener);
	}

	/**
	 * Notifies all listeners.
	 * 
	 * @param objType
	 *            The type of the object processed next.
	 * @param objName
	 *            Name of the object processed next.
	 */
	protected void notifyNextAction(String objType, String objName) {
		if (listeners == null) {
			return;
		}

		for (ProgressListener listener : listeners) {
			listener.nextAction(objType, objName);
		}
	}
}
