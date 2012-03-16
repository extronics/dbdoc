package dbdoc.impl.mysql;

import java.util.List;

import dbdoc.reflect.Database;
import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class MySQLSchema implements Schema {
	private Database database;
	private List<Table> tables;
	private List<Procedure> procedures;
	private String name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Schema#getComment()
	 */
	@Override
	public String getComment() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Schema#getDatabase()
	 */
	@Override
	public Database getDatabase() {
		return database;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Schema#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Schema#getProcedures()
	 */
	@Override
	public List<Procedure> getProcedures() {
		return procedures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.reflect.Schema#getTables()
	 */
	@Override
	public List<Table> getTables() {
		return tables;
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
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	/**
	 * @param tables
	 *            the tables to set
	 */
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * @param procedures
	 *            the procedures to set
	 */
	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}
}
