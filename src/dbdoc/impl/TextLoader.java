package dbdoc.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import dbdoc.DocException;
import dbdoc.reflect.Column;
import dbdoc.reflect.Database;
import dbdoc.reflect.Procedure;
import dbdoc.reflect.Schema;
import dbdoc.reflect.Table;

/**
 * The text loader makes it possible to store additional comment texts in text
 * files and load these comment texts.
 * 
 * @author jk
 */
public class TextLoader {
	private File baseDir;

	/**
	 * Creates a new text loader using the specified directory as base directory
	 * for all comment files.
	 * 
	 * @param baseDir
	 */
	public TextLoader(File baseDir) {
		this.baseDir = baseDir;
	}

	/**
	 * Loads a database comment.
	 * 
	 * @return The comment or <tt>null</tt> if there is no comment.
	 */
	public String getDatabaseComment() {
		return getComment(null, "database");
	}

	/**
	 * Loads a table comment.
	 * 
	 * @param table
	 *            The table for which a comment is to be loaded.
	 * @return The comment or <tt>null</tt> if there is no comment.
	 */
	public String getTableComment(Table table) {
		Schema schema = table.getSchema();
		Database db = schema.getDatabase();
		String name = null;
		if (db.isSupportingSchemas()) {
			name = schema.getName() + "." + table.getName();
		} else {
			name = table.getName();
		}
		return getComment("table", name);
	}

	/**
	 * Loads a column comment.
	 * 
	 * @param column
	 *            The column for which a comment is to be loaded.
	 * @return The comment or <tt>null</tt> if there is no comment.
	 */
	public String getColumnComment(Column column) {
		Table table = column.getTable();
		Schema schema = table.getSchema();
		Database db = schema.getDatabase();
		String name = null;
		if (db.isSupportingSchemas()) {
			name = schema.getName() + table.getName() + "." + column.getName();
		} else {
			name = table.getName() + "." + column.getName();
		}
		return getComment("column", name);
	}

	/**
	 * Loads a procedure comment.
	 * 
	 * @param procedure
	 *            The procedure for which a comment is to be loaded.
	 * @return The comment or <tt>null</tt> if there is no comment.
	 */
	public String getProcedureComment(Procedure procedure) {
		Schema schema = procedure.getSchema();
		Database db = schema.getDatabase();
		String name = null;
		if (db.isSupportingSchemas()) {
			name = schema.getName() + "." + procedure.getName();
		} else {
			name = procedure.getName();
		}
		return getComment("procedure", name);
	}

	/**
	 * Reads the comment with the specified prefix.
	 * 
	 * @param prefix
	 * @param name
	 * @return
	 */
	protected String getComment(String prefix, String name) {
		String fileName = null;
		if(prefix != null) {
			fileName = prefix + "." + name + ".txt";
		} else {
			fileName = name + ".txt";
		}
		File file = new File(baseDir.getAbsolutePath() + File.separator
				+ fileName);

		if (!file.exists()) {
			return null;
		}
		if (!file.canRead()) {
			throw new DocException("Comment file %s is not readable.", fileName);
		}

		return getContent(file);
	}

	/**
	 * Reads the content of the specified file into a string.
	 * 
	 * @param file
	 * @return The file's content or <tt>null</tt> if the file doesn't exist.
	 * @throws DocException
	 *             if the file is not readable.
	 */
	private String getContent(File file) {
		try {
			Reader reader = new BufferedReader(new FileReader(file));
			StringBuilder builder = new StringBuilder(512);
			char[] buffer = new char[512];
			int read = -1;
			while ((read = reader.read(buffer)) != -1) {
				builder.append(buffer, 0, read);
			}
			reader.close();
			return builder.toString();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException ex) {
			throw new DocException(ex);
		}
	}
}
