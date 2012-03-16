/**
 * 
 */
package dbdoc;

/**
 * @author jk
 *
 */
public class Assert {
	public static void notNull(Object value, String name)
	{
		if(value == null) {
			throw new AssertionFailedException("'%s' can't be null.", name);
		}
	}
	
	private Assert() {}
}
