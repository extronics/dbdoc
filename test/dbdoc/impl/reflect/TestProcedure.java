package dbdoc.impl.reflect;

import java.util.List;

import org.junit.Test;

import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;

/**
 * @author jk
 *
 */
public class TestProcedure extends ReflectTest {
	@Test
	public void testGetProcedures() {
		Schema schema = testDB.getSchemas().get(0);
		List<Procedure> procedures = schema.getProcedures();
		assertNotNull(procedures);
		assertEquals(1, procedures.size());
	}
	
	@Test
	public void testProcedure() {
		Schema schema = testDB.getSchemas().get(0);
		Procedure proc = schema.getProcedures().get(0);
		
		assertEquals("testproc", proc.getName());
		assertEquals("procedure comment", proc.getComment());
	}
}
