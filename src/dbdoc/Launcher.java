package dbdoc;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import dbdoc.DocException;
import dbdoc.exporter.Exporter;
import dbdoc.exporter.ExporterFactory;
import dbdoc.impl.LoggingProgressListener;
import dbdoc.importer.Importer;
import dbdoc.importer.ImporterFactory;
import dbdoc.importer.ReflectionTree;
import dbdoc.importer.TreeModifier;
import dbdoc.importer.TreeModifierFactory;

/**
 * The command line launcher to the documentation generator.
 * 
 * @author jk
 */
public class Launcher {

	private static Logger logger = Logger.getLogger(Launcher.class);

	private Arguments args;
	private Properties properties;
	private ImporterFactory importerFactory;
	private ExporterFactory exporterFactory;

	/**
	 * Constructs a new launcher with the specified command line arguments and
	 * configuration properties.
	 * 
	 * @param args
	 * @param properties
	 */
	public Launcher(Arguments args, Properties properties) {
		this.args = args;
		this.properties = properties;

		importerFactory = new ImporterFactory(properties);
		exporterFactory = new ExporterFactory(properties);

		checkArguments();
	}

	/**
	 * Starts the generation process.
	 */
	public void run() {
		String importerName = args.getString("importer");
		String exporterName = args.getString("exporter");
		Importer importer = importerFactory.getInstance(importerName);
		Exporter exporter = exporterFactory.getInstance(exporterName);
		Logger importLogger = Logger.getLogger(importer.getClass());
		Logger exportLogger = Logger.getLogger(exporter.getClass());
		importer.addListener(new LoggingProgressListener(importLogger));
		exporter.addListener(new LoggingProgressListener(exportLogger));

		ReflectionTree tree = importer.run(args);
		applyModifiers(tree);
		exporter.run(args, tree.root);
	}

	/**
	 * Checks the importer arguments for enabled tree modifiers and applies them
	 * to the reflection tree.
	 * 
	 * @param tree
	 * @param args
	 */
	private void applyModifiers(ReflectionTree tree) {
		List<String> mods = args.getStrings("mod");
		if (mods == null) {
			return;
		}

		logger.debug("Running modifiers");

		TreeModifierFactory factory = new TreeModifierFactory(properties);
		for (String mod : mods) {
			logger.debug("Applying modifier " + mod);
			TreeModifier modifier = factory.getInstance(mod);
			modifier.filterTree(tree);
		}
	}

	/**
	 * Checks wether all requried command line arguments are set and throws
	 * exceptions if arguments are missing or invalid.
	 * 
	 * @throws MissingArgumentException
	 *             if an argument is missing.
	 * @throws InvalidArgumentException
	 *             if an argument has an invalid value.
	 */
	private void checkArguments() {
		if (args.getString("importer") == null) {
			throw new MissingArgumentException("Must specify an importer.");
		}

		if (args.getString("exporter") == null) {
			throw new MissingArgumentException("Must specifiy an exporter.");
		}

		if (!importerFactory.hasClassFor(args.getString("importer"))) {
			throw new InvalidArgumentException("Unknown importer type '%s'",
					args.getString("importer"));
		}

		if (!exporterFactory.hasClassFor(args.getString("exporter"))) {
			throw new InvalidArgumentException("Unknown exporter type '%s'",
					args.getString("importer"));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String file = "resource://config/dbdoc.properties";
		Properties properties = null;

		// Setup logging
		try {
			InputStream stream = IOUtil.getResourceStream(file);
			properties = new Properties();
			properties.load(stream);
			PropertyConfigurator.configure(properties);
		} catch (IOException ex) {
			throw new DocException("Failed loading settings from '%s'", file);
		}

		try {
			Launcher launcher = new Launcher(new Arguments(args), properties);
			launcher.run();
		} catch (MissingArgumentException ex) {
			logger.error(ex.getMessage());
			usage();
		} catch (InvalidArgumentException ex) {
			logger.error(ex.getMessage());
			usage();
		}
	}

	private static void usage() {
		System.out.println("Usage: java -jar dbdoc.jar <options>");
		System.out.println();
		System.out.println("Options:");
		System.out.println("    --importer    Importer type, like 'mysql'. Specifies the type of database");
		System.out.println("                  to be documented.");
		System.out.println("    --exporter    Exporter type, like 'html'. Specifies the documentation");
		System.out.println("                  format.");
		System.out.println("    --mod         Allows to add modifiers which can add or change information.");
		System.out.println("    --text-dir    Specifies a directory from which additional documentation is");
		System.out.println("                  read.");
		System.out.println();
		System.out.println("Importers:");
		System.out.println("    mysql            Imports documentation from a mysql database.");
		System.out.println("    --mysql-host     The database host.");
		System.out.println("    --mysql-port     The mysql port, defaults to 3306.");
		System.out.println("    --mysql-db       The database name.");
		System.out.println("    --mysql-username The database user, defaults to 'root'.");
		System.out.println("    --mysql-password The database password, defaults to ''.");
		System.out.println("    The modifier 'mysql-ifk' recognizes columns named like <tablename>_<colname>");
		System.out.println("    as a reference to the table and column and adds an foreign key to the docs.");
		System.out.println();
		System.out.println("Exporters:");
		System.out.println("    html          Exporters the documentation in html format.");
		System.out.println("    --html-dir    The directory to which the documentation files are written.");
	}
}
