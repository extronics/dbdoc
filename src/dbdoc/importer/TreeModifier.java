package dbdoc.importer;

/**
 * Tree modifiers make it possible to modify an reflection tree after it was
 * built from the database meta data. They can be used as pluggable modifiers to
 * add or remove information from a reflection tree.
 * 
 * @author jk
 */
public interface TreeModifier {

	/**
	 * Inputs a reflection tree for modification.
	 * 
	 * @param tree
	 *            The tree to modify.
	 */
	public void filterTree(ReflectionTree tree);
}
