package dbdoc.impl.reflect;

import java.util.List;

import org.junit.Test;

import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class TestSchema extends ReflectTest {
	
	@Test
	public void testName() {
		Schema schema = testDB.getSchemas().get(0);
		
		if(testDB.isSupportingSchemas()) {
			assertNotNull(schema.getName());
		} else {
			assertNull(schema.getName());
		}
	}
	
	@Test
	public void testTables() {
		Schema schema = testDB.getSchemas().get(0);
		List<Table> tables = schema.getTables();
		
		assertNotNull(tables);
		assertTrue(tables.size() == 4);
	}
}
