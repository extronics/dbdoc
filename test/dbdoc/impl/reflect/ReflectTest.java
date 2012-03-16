package dbdoc.impl.reflect;

import junit.framework.TestCase;

import dbdoc.importer.Importer;
import dbdoc.importer.ImporterFactory;
import dbdoc.reflect.Database;
import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class ReflectTest extends TestCase {
	protected Database testDB;

	public ReflectTest() {
		ImporterFactory factory = new ImporterFactory(SuiteRegistry.getProps());
		Importer importer = factory.getInstance(SuiteRegistry.getName());
		testDB = importer.run(SuiteRegistry.getArgs()).root;
	}

	protected Table getTable(String name) {
		for (Table table : testDB.getSchemas().get(0).getTables()) {
			if (name.equals(table.getName())) {
				return table;
			}
		}
		throw new IllegalStateException("Missing test table '" + name + "'");
	}
}
