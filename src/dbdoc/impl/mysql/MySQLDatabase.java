package dbdoc.impl.mysql;

import java.util.List;

import dbdoc.reflect.Database;
import dbdoc.reflect.Schema;

/**
 * @author jk
 * 
 */
public class MySQLDatabase implements Database {

	private String comment;
	private String name;
	private List<Schema> schemas;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Database#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Database#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Database#getSchemas()
	 */
	@Override
	public List<Schema> getSchemas() {
		return schemas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Database#isSupportingSchemas()
	 */
	@Override
	public boolean isSupportingSchemas() {
		return false;
	}

	//
	// Internal getters/setters not part of the public api.
	//

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param schemas
	 *            the schemas to set
	 */
	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
