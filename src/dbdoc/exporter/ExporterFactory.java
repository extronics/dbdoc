package dbdoc.exporter;

import java.util.Properties;

import dbdoc.Factory;

/**
 * @author jk
 */
public class ExporterFactory extends Factory<Exporter> {
	public ExporterFactory(Properties props) {
		super(props, "exporter");
	}
}
