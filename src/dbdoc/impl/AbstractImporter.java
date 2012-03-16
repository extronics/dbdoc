package dbdoc.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import dbdoc.DocException;
import dbdoc.IOUtil;
import dbdoc.ProgressListener;
import dbdoc.importer.Importer;

/**
 * @author jk
 * 
 */
public abstract class AbstractImporter implements Importer {

	private Set<ProgressListener> listeners;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.importer.Importer#addListener(dbdoc.ProgressListener)
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

	/**
	 * Builds a standard SqlMap client by using the specified parameters.
	 * 
	 * @param driver
	 *            The driver name.
	 * @param url
	 *            The database connection url.
	 * @param username
	 *            The database user.
	 * @param password
	 *            The database password.
	 * @param sql
	 *            The resource name of the SqlMap file to be used-
	 * @return The SqlMap client.
	 */
	protected SqlMapClient buildSqlMapClient(String driver, String url,
			String username, String password, String sql) {

		InputStream stream;
		try {
			stream = IOUtil
					.getResourceStream("resource://config/sqlmap.xml");
		} catch (IOException ex) {
			throw new DocException("Unexpected exception.", ex);
		}

		Properties props = new Properties();
		props.put("db.driver", driver);
		props.put("db.url", url);
		props.put("db.username", username);
		props.put("db.password", password);
		props.put("db.sql", sql);
		props.put("db.driver", driver);

		return SqlMapClientBuilder.buildSqlMapClient(stream, props);
	}
}
