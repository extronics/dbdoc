package dbdoc.impl.reflect;

import java.util.List;

import org.junit.Test;

import dbdoc.reflect.Column;
import dbdoc.reflect.Constraint;
import dbdoc.reflect.ForeignKey;
import dbdoc.reflect.PrimaryKey;
import dbdoc.reflect.Table;
import dbdoc.reflect.Unique;

/**
 * @author jk
 * 
 */
public class TestTableConstraints extends ReflectTest {

	@Test
	public void testGetConstraints() {
		Table table = getTable("indexed01");

		assertNotNull(table.getConstraints());
		List<Constraint> constraints = table.getConstraints();
		assertEquals(3, table.getConstraints().size());

		PrimaryKey pkey = null;
		Unique unique = null;
		ForeignKey fkey = null;

		for (Constraint c : constraints) {
			if (c instanceof PrimaryKey) {
				pkey = (PrimaryKey) c;
			} else if (c instanceof Unique) {
				unique = (Unique) c;
			} else if (c instanceof ForeignKey) {
				fkey = (ForeignKey) c;
			}
		}

		assertNotNull(pkey);
		assertNotNull(unique);
		assertNotNull(fkey);
	}

	@Test
	public void testPrimaryKey() {
		Table table = getTable("indexed01");
		List<Constraint> constraints = table.getConstraints();
		PrimaryKey key = null;
		for (Constraint c : constraints) {
			if (c instanceof PrimaryKey) {
				key = (PrimaryKey) c;
			}
		}

		assertNotNull(key);
		assertNotNull(key.getName());
		assertNotNull(key.getColumns());
		assertEquals(1, key.getColumns().size());

		Column column = key.getColumns().get(0);
		assertEquals("id", column.getName());
		assertTrue(column.getPrimaryKey() == key);
	}

	@Test
	public void testUnique() {
		Table table = getTable("indexed01");
		List<Constraint> constraints = table.getConstraints();
		Unique key = null;
		for (Constraint c : constraints) {
			if (c instanceof Unique) {
				key = (Unique) c;
			}
		}

		assertNotNull(key);
		assertNotNull(key.getName());
		assertNotNull(key.getColumns());
		assertEquals(1, key.getColumns().size());

		Column column = key.getColumns().get(0);
		assertEquals("name", column.getName());
	}

	@Test
	public void testForeignKey() {
		Table table = getTable("indexed01");
		List<Constraint> constraints = table.getConstraints();
		ForeignKey key = null;
		for (Constraint c : constraints) {
			if (c instanceof ForeignKey) {
				key = (ForeignKey) c;
			}
		}

		assertNotNull(key);
		assertNotNull(key.getName());
		assertNotNull(key.getColumns());
		assertEquals(1, key.getColumns().size());

		Column column = key.getColumns().get(0);
		assertEquals("indexed02_id", column.getName());

		assertNotNull(key.getForeignColumns());
		assertEquals(1, key.getForeignColumns().size());

		column = key.getForeignColumns().get(0);
		assertEquals("id", column.getName());
		assertNotNull(column.getTable());
		assertEquals("indexed02", column.getTable().getName());
	}
}
