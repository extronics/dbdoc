/**
 * 
 */
package dbdoc.importer;

import java.util.Properties;

import dbdoc.Factory;

/**
 * @author jk
 */
public class ImporterFactory extends Factory<Importer> {
	public ImporterFactory(Properties props) {
		super(props, "importer");
	}
}
