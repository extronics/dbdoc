package dbdoc.impl.reflect;

import java.util.List;

import org.junit.Test;

import dbdoc.reflect.Column;
import dbdoc.reflect.Index;
import dbdoc.reflect.Table;

/**
 * @author jk
 * 
 */
public class TestColumn extends ReflectTest {

	@Test
	public void testLink() {
		Table table = getTable("commented");

		assertNotNull(table.getColumns());
		assertEquals(3, table.getColumns().size());

		Column column = table.getColumns().get(0);
		assertEquals("id", column.getName());

		assertNotNull(column.getTable());
		assertEquals("commented", column.getTable().getName());
		assertTrue(table == column.getTable());
	}

	@Test
	public void testAttributes01() {
		Table table = getTable("commented");

		assertNotNull(table.getColumns());
		assertEquals(3, table.getColumns().size());

		Column column = table.getColumns().get(0);
		assertEquals("id", column.getName());
		assertNotNull(column.getDataType());
		assertTrue(column.getDataLength() > 0);
		assertNull(column.getDefaultValue());
		assertFalse(column.isNullable());
		assertTrue(column.isPrimaryKeyColumn());
		assertTrue(column.isConstrainedColumn());
		assertFalse(column.isIndexColumn());
		assertNotNull(column.getPrimaryKey());
		assertFalse(column.isReferenced());
		assertFalse(column.isReferencing());
	}

	@Test
	public void testAttributes02() {
		Table table = getTable("commented");

		assertNotNull(table.getColumns());
		assertEquals(3, table.getColumns().size());

		Column column = table.getColumns().get(1);
		assertEquals("name", column.getName());
		assertNotNull(column.getDataType());
		assertEquals(55, column.getDataLength());
		assertEquals("default", column.getDefaultValue());
		assertTrue(column.isNullable());
		assertFalse(column.isPrimaryKeyColumn());
		assertFalse(column.isConstrainedColumn());
		assertFalse(column.isIndexColumn());
		assertNull(column.getPrimaryKey());
		assertFalse(column.isReferenced());
		assertFalse(column.isReferencing());
	}

	@Test
	public void testAttributes03() {
		Table table = getTable("commented");

		assertNotNull(table.getColumns());
		assertEquals(3, table.getColumns().size());

		Column column = table.getColumns().get(2);
		assertEquals("date", column.getName());
		assertNotNull(column.getDataType());
		assertEquals(0, column.getDataLength());
		assertNull(column.getDefaultValue());
		assertTrue(column.isNullable());
		assertFalse(column.isPrimaryKeyColumn());
		assertFalse(column.isConstrainedColumn());
		assertFalse(column.isIndexColumn());
		assertNull(column.getPrimaryKey());
		assertFalse(column.isReferenced());
		assertFalse(column.isReferencing());
	}

	@Test
	public void testAttributes04() {
		Table table = getTable("indexed01");

		assertNotNull(table.getColumns());
		assertEquals(4, table.getColumns().size());

		Column column = table.getColumns().get(3);
		assertEquals("indexed02_id", column.getName());
		assertFalse(column.isReferenced());
		assertTrue(column.isReferencing());
	}

	@Test
	public void testAttributes05() {
		Table table = getTable("indexed01");

		assertNotNull(table.getColumns());
		assertEquals(4, table.getColumns().size());

		Column column = table.getColumns().get(0);
		assertEquals("id", column.getName());
		assertTrue(column.isSequence());
	}

	@Test
	public void testAttributes06() {
		Table table = getTable("indexed02");

		assertNotNull(table.getColumns());
		assertEquals(2, table.getColumns().size());

		Column column = table.getColumns().get(0);
		assertEquals("id", column.getName());
		assertTrue(column.isReferenced());
		assertFalse(column.isReferencing());
		assertTrue(column.isSequence());
	}

	@Test
	public void testForeignKeyOut() {
		Table table = getTable("indexed01");
		Column column = table.getColumns().get(3);

		assertEquals("indexed02_id", column.getName());
		assertTrue(column.isReferencing());

		List<Column> referenced = column.getReferencedColumns();
		assertNotNull(referenced);
		assertEquals(1, referenced.size());

		Column refCol = referenced.get(0);
		assertEquals("id", refCol.getName());
		assertEquals("indexed02", refCol.getTable().getName());
	}

	@Test
	public void testForeignKeyIn() {
		Table table = getTable("indexed02");
		Column column = table.getColumns().get(0);

		assertEquals("id", column.getName());
		assertTrue(column.isReferenced());

		List<Column> referencing = column.getReferencingColumns();
		assertNotNull(referencing);
		assertEquals(1, referencing.size());

		Column refCol = referencing.get(0);
		assertEquals("indexed02_id", refCol.getName());
		assertEquals("indexed01", refCol.getTable().getName());
	}
	
	@Test
	public void testIndexes01() {
		Table table = getTable("indexed01");
		Column column = table.getColumns().get(1);
		
		assertEquals("name", column.getName());
		assertTrue(column.isConstrainedColumn());
		assertEquals(1, column.getConstraints().size());
	}
	
	@Test
	public void testIndexes02() {
		Table table = getTable("indexed01");
		Column column = table.getColumns().get(2);
		
		assertEquals("date", column.getName());
		assertFalse(column.isConstrainedColumn());
		assertTrue(column.isIndexColumn());
		assertEquals(1, column.getIndexes().size());
		
		Index index = column.getIndexes().get(0);
		assertEquals("date", index.getName());
	}
}
