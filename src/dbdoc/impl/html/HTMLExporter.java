package dbdoc.impl.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import dbdoc.DocException;
import dbdoc.Arguments;
import dbdoc.InvalidArgumentException;
import dbdoc.MissingArgumentException;
import dbdoc.ProgressListener;
import dbdoc.impl.AbstractExporter;
import dbdoc.reflect.Database;
import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * @author jk
 * 
 */
public class HTMLExporter extends AbstractExporter {

	private Configuration cfg;
	private File baseDir;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbdoc.exporter.Exporter#run(dbdoc.Arguments,
	 * dbdoc.reflect.Database)
	 */
	@Override
	public void run(Arguments args, Database db) {
		checkArguments(args);

		baseDir = new File(args.getString("dir", "html"));

		if (!baseDir.exists()) {
			if (!baseDir.mkdirs()) {
				throw new InvalidArgumentException(
						"Failed creating directory '%s'", baseDir.getName());
			}
		}

		try {
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(
				new File(
					Thread.currentThread()
					.getContextClassLoader()
					.getResource("html/layout")
					.getFile()
				)
			);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setSharedVariable("Database", db);
			cfg.addAutoInclude("lib.ftl");
		} catch (Exception ex) {
			throw new DocException(ex);
		}
		
		notifyNextAction(ProgressListener.T_DB, db.getName());
		writeTemplate("index.ftl", "index.html", null);
		writeTemplate("menu.ftl", "menu.html", null);
		writeTemplate("main.ftl", "main.html", null);

		if (db.isSupportingSchemas()) {
			for (Schema schema : db.getSchemas()) {
				exportSchema(schema);
			}
		}

		for (Schema schema : db.getSchemas()) {
			for (Table table : schema.getTables()) {
				exportTable(table);
			}

			if (schema.getProcedures() != null) {
				for (Procedure proc : schema.getProcedures()) {
					exportProcedure(proc);
				}
			}
		}
		
		exportResource("screen.css");
		exportResource("db.png");
		exportResource("db_big.png");
		exportResource("table.png");
		exportResource("left.png");
		exportResource("right.png");
		exportResource("mootools.js");
		exportResource("doc.js");
		exportResource("bullet.png");
		exportResource("proc.png");
		
		exportResource("sql/help.png");
		exportResource("sql/magnifier.png");
		exportResource("sql/page_white_code.png");
		exportResource("sql/page_white_copy.png");
		exportResource("sql/printer.png");
		exportResource("sql/shBrushSql.js");
		exportResource("sql/shCore.css");
		exportResource("sql/shCore.js");
		exportResource("sql/shThemeDefault.css");
		exportResource("sql/wrapping.png");
	}

	private void exportSchema(Schema schema) {
		notifyNextAction(ProgressListener.T_SCHEMA, schema.getName());
		// TODO implement
	}

	private void exportTable(Table table) {
		notifyNextAction(ProgressListener.T_TABLE, table.getName());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("Table", table);
		String file = "table." + table.getName() + ".html";
		writeTemplate("table.ftl", file, model);
	}

	private void exportProcedure(Procedure proc) {
		notifyNextAction(ProgressListener.T_PROCEDURE, proc.getName());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("Procedure", proc);
		String file = "procedure." + proc.getName() + ".html";
		writeTemplate("proc.ftl", file, model);
	}

	private void exportResource(String name) {
		try {
			String inName = "html/layout/" + name;
			File outName = new File(baseDir.getAbsolutePath() + File.separator + name);
			File dir = outName.getParentFile();
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			ClassLoader loader = getClass().getClassLoader();
			InputStream in = loader.getResourceAsStream(inName);
			if (in == null) {
				throw new DocException("Resource %s not found", inName);
			}
			OutputStream out = new FileOutputStream(outName);
			byte[] buffer = new byte[4096];
			int read = -1;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			out.close();
		} catch (IOException ex) {
			throw new DocException(ex);
		}
	}

	private void writeTemplate(String template, String file,
			Map<String, Object> model) {
		try {
			Template tpl = cfg.getTemplate(template);
			String absName = baseDir.getAbsolutePath() + File.separator + file;
			Writer out = new OutputStreamWriter(new FileOutputStream(absName));
			tpl.process(model, out);
			out.close();
		} catch (IOException ex) {
			throw new DocException(ex);
		} catch (TemplateException ex) {
			throw new DocException(ex);
		}
	}

	/**
	 * Ensures that all required arguments are provided.
	 * 
	 * @param args
	 */
	private void checkArguments(Arguments args) {
		if (args.getString("dir", "html") == null) {
			throw new MissingArgumentException(
					"Must specify the output directory.");
		}
	}
}
