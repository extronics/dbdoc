package dbdoc.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import dbdoc.TestCase;
import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;
import dbdoc.reflect.Database;
import dbdoc.reflect.ForeignKey;
import dbdoc.reflect.Index;
import dbdoc.reflect.PrimaryKey;
import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;
import dbdoc.reflect.Trigger;

/**
 * @author jk
 * 
 */
public class TestTextLoader extends TestCase {

	protected File getBaseDir() {
		return new File(getClass().getResource("texts").getPath());
	}

	@Test
	public void testGetDatabaseComment() {
		TextLoader loader = new TextLoader(getBaseDir());
		assertEquals("database comment", loader
				.getDatabaseComment());
	}

	@Test
	public void testGetTableComment() {
		TextLoader loader = new TextLoader(getBaseDir());
		assertEquals("table comment", loader
				.getTableComment(new TableStub("commented", new SchemaStub(
						null, new DatabaseStub("doctest")))));
	}

	@Test
	public void testGetProcedureComment() {
		TextLoader loader = new TextLoader(getBaseDir());
		assertEquals("procedure comment", loader
				.getProcedureComment(new ProcedureStub("testproc",
						new SchemaStub(null, new DatabaseStub("doctest")))));
	}

	@Test
	public void testGetProcedureCommentFailed02() {
		TextLoader loader = new TextLoader(getBaseDir());
		assertNull(loader.getProcedureComment(new ProcedureStub("testproc2",
				new SchemaStub(null, new DatabaseStub("doctest")))));
	}

	//
	//
	//

	public static class DatabaseStub implements Database {
		private String name;

		public DatabaseStub(String name) {
			this.name = name;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public List<Schema> getSchemas() {
			return null;
		}

		@Override
		public boolean isSupportingSchemas() {
			return false;
		}
	}

	public static class SchemaStub implements Schema {
		private String name;
		private Database database;

		public SchemaStub(String name, Database database) {
			this.name = name;
			this.database = database;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public Database getDatabase() {
			return database;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public List<Procedure> getProcedures() {
			return null;
		}

		@Override
		public List<Table> getTables() {
			return null;
		}
	}

	public static class TableStub implements Table {
		private String name;
		private Schema schema;

		public TableStub(String name, Schema schema) {
			this.name = name;
			this.schema = schema;
		}

		@Override
		public List<Column> getColumns() {
			return null;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public List<Constraint> getConstraints() {
			return null;
		}

		@Override
		public List<Index> getIndexes() {
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Schema getSchema() {
			return schema;
		}

		@Override
		public List<Trigger> getTriggers() {
			return null;
		}

		@Override
		public PrimaryKey getPrimaryKey() {
			return null;
		}

		@Override
		public List<ForeignKey> getForeignKeysIn() {
			return null;
		}

		@Override
		public List<ForeignKey> getForeignKeysOut() {
			return null;
		}
	}

	public static class ProcedureStub implements Procedure {
		private String name;
		private Schema schema;

		public ProcedureStub(String name, Schema schema) {
			this.name = name;
			this.schema = schema;
		}

		@Override
		public String getComment() {
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Schema getSchema() {
			return schema;
		}

		@Override
		public String getBody() {
			return null;
		}
	}
}
