package dbdoc.impl.mysql;

import dbdoc.reflect.StatementType;
import dbdoc.reflect.Timing;
import dbdoc.reflect.Trigger;

/**
 * @author jk
 * 
 */
public class MySQLTrigger implements Trigger {

	private String tableName;
	private String name;
	private String body;
	private StatementType statementType;
	private Timing timing;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#getStatementType()
	 */
	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#getTiming()
	 */
	@Override
	public Timing getTiming() {
		return timing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#isAfter()
	 */
	@Override
	public boolean isAfter() {
		return getTiming() == Timing.AFTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#isBefore()
	 */
	@Override
	public boolean isBefore() {
		return getTiming() == Timing.BEFORE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#isOnDelete()
	 */
	@Override
	public boolean isOnDelete() {
		return getStatementType() == StatementType.DELETE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#isOnInsert()
	 */
	@Override
	public boolean isOnInsert() {
		return getStatementType() == StatementType.INSERT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#isOnUpdate()
	 */
	@Override
	public boolean isOnUpdate() {
		return getStatementType() == StatementType.UPDATE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Trigger#getBody()
	 */
	@Override
	public String getBody() {
		return body;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param statementType
	 *            the statementType to set
	 */
	public void setStatementType(StatementType statementType) {
		this.statementType = statementType;
	}

	/**
	 * @param timing
	 *            the timing to set
	 */
	public void setTiming(Timing timing) {
		this.timing = timing;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
