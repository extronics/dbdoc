package dbdoc.importer;

import java.util.Properties;

import dbdoc.Factory;

/**
 * @author jk
 */
public class TreeModifierFactory extends Factory<TreeModifier> {
	public TreeModifierFactory(Properties props) {
		super(props, "modifier");
	}
}
