package dbdoc.impl.reflect;

import org.junit.Test;

import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class TestTableComments extends ReflectTest {
	
	@Test
	public void testTableComment() {
		Table table = getTable("commented");
		
		assertEquals("Table level comment", table.getComment());
	}
}
