package dbdoc.impl.mysql;

import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import dbdoc.Arguments;
import dbdoc.impl.reflect.SuiteRegistry;
import dbdoc.impl.reflect.TestColumn;
import dbdoc.impl.reflect.TestDatabase;
import dbdoc.impl.reflect.TestProcedure;
import dbdoc.impl.reflect.TestSchema;
import dbdoc.impl.reflect.TestTable;
import dbdoc.impl.reflect.TestTableComments;
import dbdoc.impl.reflect.TestTableConstraints;
import dbdoc.impl.reflect.TestTrigger;

import org.apache.log4j.PropertyConfigurator;

public class MySQLTestSuite {
	public static Test suite() {
		Properties props = new Properties();
		props.put("importer.mysql", "dbdoc.impl.mysql.MySQLImporter");
		props.put("log4j.rootLogger", "INFO, A1");
		props.put("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
		props.put("log4j.appender.A1.layout", "org.apache.log4j.PatternLayout");
		props.put("log4j.appender.A1.layout.ConversionPattern", "%p: %c{1} - %m%n");

		PropertyConfigurator.configure(props);

		Arguments args = new Arguments(new String[] { "--mysql-host",
				"localhost", "--mysql-db", "doctest", "--mysql-username", "doctest" });
		SuiteRegistry.setSuite(props, args, "mysql");
		
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestDatabase.class);
		suite.addTestSuite(TestSchema.class);
		suite.addTestSuite(TestTable.class);
		suite.addTestSuite(TestTableComments.class);
		suite.addTestSuite(TestTableConstraints.class);
		suite.addTestSuite(TestColumn.class);
		suite.addTestSuite(TestTrigger.class);
		suite.addTestSuite(TestProcedure.class);
		return suite;
	}
}
