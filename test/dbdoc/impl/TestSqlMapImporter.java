package dbdoc.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;
import dbdoc.Arguments;
import dbdoc.TestCase;
import dbdoc.importer.ReflectionTree;

/**
 * @author jk
 * 
 */
public class TestSqlMapImporter extends TestCase {
	protected static SqlMapClient client;

	@BeforeClass
	public static void beforeClass() {
		Properties props = new Properties();
		props.setProperty("log4j.rootLogger", "DEBUG, A1");
		props.setProperty("log4j.appender.A1",
				"org.apache.log4j.ConsoleAppender");
		props.setProperty("log4j.appender.A1.layout",
				"org.apache.log4j.PatternLayout");
		props.setProperty("log4j.appender.A1.layout.ConversionPattern",
				"%-4r [%t] %-5p %c %x - %m%n");
		PropertyConfigurator.configure(props);

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/dbdoc";
		String username = "root";
		String password = "";
		String sql = "dbdoc/impl/mysql/mysql.map.xml";

		client = new AbstractImporterStub().getSqlMapClient(driver, url,
				username, password, sql);
	}

	@Test
	public void testGetTables() throws SQLException {
		Map<String, String> query = new HashMap<String, String>();
		query.put("schema", "dbdoc");
		query.put("table", "blah2");
		client.queryForList("Reflect.getProcedures", "dbdoc");
	}

	//
	//
	//

	public static class AbstractImporterStub extends AbstractImporter {
		public SqlMapClient getSqlMapClient(String driver, String url,
				String username, String password, String sql) {
			return buildSqlMapClient(driver, url, username, password, sql);
		}

		@Override
		public ReflectionTree run(Arguments args) {
			throw new UnsupportedOperationException();
		}
	}
}
