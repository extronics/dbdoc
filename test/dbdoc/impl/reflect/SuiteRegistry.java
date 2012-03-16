package dbdoc.impl.reflect;

import java.util.Properties;

import dbdoc.Arguments;
import dbdoc.Assert;

/**
 * @author jk
 * 
 */
public class SuiteRegistry {
	private SuiteRegistry() {
	}

	private static Properties props;
	private static Arguments args;
	private static String name;

	public static void setSuite(Properties props, Arguments args, String name) {
		SuiteRegistry.props = props;
		SuiteRegistry.args = args;
		SuiteRegistry.name = name;
	}

	public static Properties getProps() {
		Assert.notNull(props, "props");
		return props;
	}

	public static Arguments getArgs() {
		Assert.notNull(args, "args");
		return args;
	}

	public static String getName() {
		Assert.notNull(name, "name");
		return name;
	}
}
