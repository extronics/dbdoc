package dbdoc.impl.reflect;

import org.junit.Test;

import dbdoc.reflect.StatementType;
import dbdoc.reflect.Table;
import dbdoc.reflect.Timing;
import dbdoc.reflect.Trigger;

/**
 * @author jk
 *
 */
public class TestTrigger extends ReflectTest {
	@Test
	public void testGetTriggers() {
		Table table = getTable("triggers");
		
		assertNotNull(table.getTriggers());
		assertEquals(2, table.getTriggers().size());
	}
	
	@Test
	public void testTrigger1() {
		Table table = getTable("triggers");
		Trigger trigger = table.getTriggers().get(0);
		
		assertEquals("testtrigger", trigger.getName());
		assertEquals(StatementType.UPDATE, trigger.getStatementType());
		assertEquals(Timing.BEFORE, trigger.getTiming());
	}
	
	@Test
	public void testTrigger2() {
		Table table = getTable("triggers");
		Trigger trigger = table.getTriggers().get(1);
		
		assertEquals("testtrigger2", trigger.getName());
		assertEquals(StatementType.UPDATE, trigger.getStatementType());
		assertEquals(Timing.AFTER, trigger.getTiming());
	}
}
