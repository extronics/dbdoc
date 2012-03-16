package dbdoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * @author jk
 * 
 */
public class TestArguments extends TestCase {
	@Test
	public void testInt() {
		String[] arr = array("--arg1", "123", "--arg2", "--arg3", "foo");
		Arguments args = new Arguments(arr);

		assertEquals(123, args.getInt("arg1", 0));
		assertEquals("123", args.getString("arg1"));
		assertTrue(args.getBool("arg1"));
	}

	@Test
	public void testBool() {
		String[] arr = array("--arg1", "123", "--arg2", "--arg3", "foo");
		Arguments args = new Arguments(arr);

		assertEquals(0, args.getInt("arg2", 0));
		assertEquals(null, args.getString("arg2"));
		assertTrue(args.getBool("arg2"));
	}

	@Test
	public void testString() {
		String[] arr = array("--arg1", "123", "--arg2", "--arg3", "foo");
		Arguments args = new Arguments(arr);

		assertEquals("foo", args.getString("arg3"));
		assertEquals(0, args.getInt("arg3", 0));
		assertTrue(args.getBool("arg3"));
	}

	@Test
	public void testMissing() {
		String[] arr = array("--arg1", "123", "--arg2", "--arg3", "foo");
		Arguments args = new Arguments(arr);

		assertNull(args.getString("arg4"));
		assertEquals(0, args.getInt("arg4", 0));
		assertFalse(args.getBool("arg4"));
	}

	@Test
	public void testGrouped() {
		String[] arr = array("--db-arg1", "123", "--db-arg2", "--db-arg3",
				"foo");
		Arguments args = new Arguments(arr);

		assertEquals("123", args.getString("arg1", "db"));
		assertTrue(args.getBool("arg2", "db"));
		assertEquals("foo", args.getString("arg3", "db"));
		assertNull(args.getString("arg4", "db"));
	}
	
	@Test
	public void testMultiInts() {
		String[] arr = array("--foo", "1", "--foo", "3", "--foo", "6", "--bar", "5");
		Arguments args = new Arguments(arr);
		
		assertEquals(1, args.getInt("foo", 0));
		List<Integer> ints = args.getInts("foo", 10);
		
		assertNotNull(ints);
		assertEquals(3, ints.size());
		assertEquals(Integer.valueOf(1), ints.get(0));
		assertEquals(Integer.valueOf(3), ints.get(1));
		assertEquals(Integer.valueOf(6), ints.get(2));
	}
	
	@Test
	public void testMultiStrings() {
		String[] arr = array("--foo", "1", "--foo", "3", "--foo", "6", "--bar", "5");
		Arguments args = new Arguments(arr);
		
		assertEquals(1, args.getInt("foo", 0));
		List<String> strings = args.getStrings("foo");
		
		assertNotNull(strings);
		assertEquals(3, strings.size());
		assertEquals("1", strings.get(0));
		assertEquals("3", strings.get(1));
		assertEquals("6", strings.get(2));
	}
}
