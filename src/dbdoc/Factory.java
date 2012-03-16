package dbdoc;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * Abstract base classs for object factories providing different implementations
 * of the same base class or interface.
 * 
 * @author jk
 */
public abstract class Factory<T> {
	private Map<String, String> classes;
	private Map<String, T> instances;

	/**
	 * Creates a new factory which reads all available implementations from the
	 * specified property set.
	 * 
	 * @param props
	 *            The property set.
	 * @param prefix
	 *            The property prefix denoting all classes to be used.
	 */
	protected Factory(Properties props, String prefix) {
		Assert.notNull(props, "props");
		Assert.notNull(prefix, "prefix");

		classes = new HashMap<String, String>();
		instances = new HashMap<String, T>();

		int prefixLen = prefix.length();
		for (Entry<Object, Object> entry : props.entrySet()) {
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();

			if (prefix.equals(key.substring(0, prefixLen))) {
				classes.put(key.substring(prefixLen + 1), val);
			}
		}
	}

	/**
	 * Returns a map of all available classes.
	 * 
	 * @return
	 */
	public Map<String, String> getAvailableClasses() {
		return classes;
	}

	/**
	 * Determines wether a class is available under the specified key.
	 * 
	 * @param key
	 *            The key of the class.
	 * @return true if there is a class with this key, false otherwise.
	 */
	public boolean hasClassFor(String key) {
		return classes.containsKey(key);
	}

	/**
	 * Returns an instance of the class with the specified key.
	 * 
	 * @param key
	 *            The key.
	 * @return An instance of the class.
	 * @throws IllegalArgumentException
	 *             if there is no class with the specified key.
	 * @throws ObjectException
	 *             if the class does not exist or can't be instantiated.
	 */
	public T getInstance(String key) {
		if (!hasClassFor(key)) {
			throw new IllegalArgumentException();
		}

		if (!instances.containsKey(key)) {
			addInstanceFor(key);
		}

		return instances.get(key);
	}

	@SuppressWarnings("unchecked")
	private void addInstanceFor(String key) {
		T instance = null;
		try {
			Class<T> cls = (Class<T>) Class.forName(classes.get(key));
			instance = cls.newInstance();
		} catch (ClassNotFoundException e) {
			throw new ObjectException("Class %s not found.", key);
		} catch (InstantiationException e) {
			throw new ObjectException("Can't instantiate class %s.", key);
		} catch (IllegalAccessException e) {
			throw new ObjectException("Can't instantiate class %s.", key);
		}
		instances.put(key, instance);
	}
}
