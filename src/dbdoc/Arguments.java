package dbdoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts an array of *nix-style command line arguments into an map and
 * provides type-safe accessor methods.
 * 
 * @author jk
 */
public class Arguments {

	private Map<String, List<String>> arguments;

	/**
	 * Creates an empty argument map.
	 */
	public Arguments() {
		arguments = new HashMap<String, List<String>>();
	}

	/**
	 * Creates an argument map with the specified array of arguments as initial
	 * values.
	 * 
	 * @param args
	 *            the initial arguments.
	 * @throws AssertionFailedException
	 *             if args is <tt>null</tt>.
	 */
	public Arguments(String[] args) {
		Assert.notNull(args, "args");
		arguments = new HashMap<String, List<String>>();
		addArguments(args);
	}

	/**
	 * Adds an array of arguments to this map.
	 * 
	 * @param args
	 *            the arguments to add.
	 */
	public void addArguments(String[] args) {
		String key = null;
		String val = null;

		for (String arg : args) {
			if (arg.charAt(0) == '-' && arg.charAt(1) == '-') {
				if (key == null) {
					key = arg.substring(2);
				} else {
					addArgument(key, val);
					key = arg.substring(2);
					val = null;
				}
			} else {
				if (key == null) {
					val = arg;
				} else {
					addArgument(key, arg);
					key = null;
					val = null;
				}
			}
		}
	}

	/**
	 * Adds an argument to the internal map.
	 * 
	 * @param key
	 *            The argument key.
	 * @param val
	 *            The argument value.
	 */
	private void addArgument(String key, String val) {
		if (!arguments.containsKey(key)) {
			arguments.put(key, new ArrayList<String>());
		}
		arguments.get(key).add(val);
	}

	/**
	 * Looks for a boolean value in this argument map.
	 * 
	 * @param key
	 *            The key.
	 * @return true if the map contains an element with the specified key, false
	 *         otherwise.
	 */
	public boolean getBool(String key) {
		return arguments.containsKey(key);
	}

	/**
	 * Same as {@link #getBool(String)}, but looks for a value within the
	 * specified group.
	 * 
	 * @param key
	 *            The key.
	 * @param group
	 *            The value's group.
	 * @return true if the map contains an element with the specified key and
	 *         group, false otherwise.
	 */
	public boolean getBool(String key, String group) {
		return getBool(group + "-" + key);
	}

	/**
	 * Looks for an integer value within this map. Returns <tt>def</tt> if the
	 * map doesn't contain a value with the specified key or if the value is not
	 * a valid integer value.
	 * 
	 * @param key
	 *            The key.
	 * @param def
	 *            The default value.
	 * @return The value with the specified key or <tt>def</tt>.
	 */
	public int getInt(String key, int def) {
		String val = getString(key);
		if (val == null) {
			return def;
		}
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException ex) {
			return def;
		}
	}

	/**
	 * Looks for integer values within this map. If any of the found values does
	 * not conform to a valid integer format, it is replaced with <tt>def</tt>.
	 * 
	 * @param key
	 *            The key.
	 * @param def
	 *            The default value.
	 * @return The values with the specified key or <tt>null</tt> if no values
	 *         match.
	 */
	public List<Integer> getInts(String key, int def) {
		List<String> vals = getStrings(key);
		if (vals == null) {
			return null;
		}

		List<Integer> ints = new ArrayList<Integer>(vals.size());
		for (String val : vals) {
			try {
				ints.add(Integer.parseInt(val));
			} catch (NumberFormatException ex) {
				ints.add(def);
			}
		}
		return ints;
	}

	/**
	 * Same as {@link #getInt(String, int)}, but looks for a value within the
	 * specified group.
	 * 
	 * @param key
	 *            The key.
	 * @param def
	 *            The default value.
	 * @param group
	 *            The value's group.
	 * @return The value with the specified key or <tt>def</tt>.
	 */
	public int getInt(String key, String group, int def) {
		return getInt(group + "-" + key, def);
	}

	/**
	 * Same as {@link #getInts(String, int)}, but looks for values within the
	 * specified group.
	 * 
	 * @param key
	 *            The key.
	 * @param def
	 *            The default value.
	 * @param group
	 *            The value group.
	 * @return The values with the specified key or <tt>null</tt>.
	 */
	public List<Integer> getInts(String key, String group, int def) {
		return getInts(group + "-" + key, def);
	}

	/**
	 * Looks for a string value within this map. Returns <tt>null</tt> if there
	 * is no value with the specified key.
	 * 
	 * @param key
	 *            The key.
	 * @return The value with this key or <tt>null</tt>
	 */
	public String getString(String key) {
		if (arguments.containsKey(key)) {
			return arguments.get(key).get(0);
		}
		return null;
	}

	/**
	 * Looks for a list of string values within this map. Returns <tt>null</tt>
	 * if there are no values with the specified key.
	 * 
	 * @param key
	 *            The key.
	 * @return The values with this key or <tt>null</tt>
	 */
	public List<String> getStrings(String key) {
		if (arguments.containsKey(key)) {
			return arguments.get(key);
		}
		return null;
	}

	/**
	 * Same as {@link #getString(String)}, but looks for a value within the
	 * specified group.
	 * 
	 * @param key
	 *            The key.
	 * @param group
	 *            The value's group.
	 * @return The value with this key or <tt>null</tt>
	 */
	public String getString(String key, String group) {
		return getString(group + "-" + key);
	}

	/**
	 * Same as {@link #getStrings(String)}, but looks for values within the
	 * specified group.
	 * 
	 * @param key
	 *            The key.
	 * @param group
	 *            The value group.
	 * @return The values with this key or <tt>null</tt>
	 */
	public List<String> getStrings(String key, String group) {
		return getStrings(group + "-" + key);
	}
}
