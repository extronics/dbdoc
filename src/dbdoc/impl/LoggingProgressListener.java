package dbdoc.impl;

import org.apache.log4j.Logger;

import dbdoc.ProgressListener;

/**
 * @author jk
 * 
 */
public class LoggingProgressListener implements ProgressListener {

	private Logger logger;

	public LoggingProgressListener(Logger logger) {
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.ProgressListener#nextAction(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void nextAction(String objType, String objName) {
		if (ProgressListener.T_COLUMN.equals(objType)) {
			return;
		}
		logger.info(String.format("Processing %s %s", objType, objName));
	}

}
