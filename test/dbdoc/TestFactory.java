package dbdoc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

/**
 * @author jk
 * 
 */
public class TestFactory extends TestCase {
	@Test
	public void testConstruct() {
		Properties props = new Properties();
		props.put("pre.class1", "dbdoc.TestFactory$TestClass1");
		props.put("pre.class2", "dbdoc.TestFactory$TestClass2");
		StubFactory factory = new StubFactory(props, "pre");

		assertTrue(factory.hasClassFor("class1"));
		assertTrue(factory.hasClassFor("class2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetObjectFailed() {
		Properties props = new Properties();
		props.put("pre.class1", "dbdoc.TestFactory$TestClass1");
		props.put("pre.class2", "dbdoc.TestFactory$TestClass2");
		StubFactory factory = new StubFactory(props, "pre");

		assertNotNull(factory.getInstance("class5"));
	}

	@Test
	public void testGetObject() {
		Properties props = new Properties();
		props.put("pre.class1", "dbdoc.TestFactory$TestClass1");
		props.put("pre.class2", "dbdoc.TestFactory$TestClass2");
		StubFactory factory = new StubFactory(props, "pre");

		assertNotNull(factory.getInstance("class1"));
	}

	@Test
	public void testGetObjectSingleton() {
		Properties props = new Properties();
		props.put("pre.class1", "dbdoc.TestFactory$TestClass1");
		props.put("pre.class2", "dbdoc.TestFactory$TestClass2");
		StubFactory factory = new StubFactory(props, "pre");

		Object obj1 = factory.getInstance("class1");
		Object obj2 = factory.getInstance("class1");
		Object obj3 = factory.getInstance("class2");

		assertTrue(obj1 == obj2);
		assertFalse(obj2 == obj3);
	}

	//
	// Test classes
	//

	public static class StubFactory extends Factory<AbstractTestClass> {
		protected StubFactory(Properties props, String prefix) {
			super(props, prefix);
		}
	}

	public static class AbstractTestClass {
	}

	public static class TestClass1 extends AbstractTestClass {
	}

	public static class TestClass2 extends AbstractTestClass {
	}
}
