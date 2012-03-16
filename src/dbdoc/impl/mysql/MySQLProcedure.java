package dbdoc.impl.mysql;

import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;

/**
 * @author jk
 * 
 */
public class MySQLProcedure implements Procedure {

	private Schema schema;
	private String name;
	private String comment;
	private String body;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Procedure#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Procedure#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Procedure#getSchema()
	 */
	@Override
	public Schema getSchema() {
		return schema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Procedure#getBody()
	 */
	@Override
	public String getBody() {
		return body;
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
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
