package dbdoc.impl.reflect;

import org.junit.Test;

import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class TestTable extends ReflectTest {
	
	
	@Test
	public void testName() {
		Table table = getTable("indexed01");
		
		assertEquals("indexed01", table.getName());
	}
	
	@Test
	public void testSchema() {
		Table table = getTable("indexed01");
		
		assertNotNull(table.getSchema());
	}

	@Test
	public void testColumns() {
		Table table = getTable("indexed01");

		assertNotNull(table.getColumns());
		assertTrue(table.getColumns().size() == 4);
	}
}
