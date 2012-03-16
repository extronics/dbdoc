package dbdoc.impl.reflect;

import org.junit.Test;

/**
 * @author jk
 */
public class TestDatabase extends ReflectTest {

	@Test
	public void testName() {
		assertNotNull(testDB.getName());
		assertEquals("doctest", testDB.getName());
	}

	@Test
	public void testSchemas() {
		assertNotNull(testDB.getSchemas());
		assertTrue(testDB.getSchemas().size() > 0);
	}
}
