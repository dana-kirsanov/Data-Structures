package proj2;
/* *
* This is an interface for a Binary Search Tree,
* which is implemented in the BST class.
* It declares methods that insert and remove an item from 
* the tree, and a method that checks if the tree contains
* a specific item.
* @author Joanna Klukowska
*/
public interface BSTInterface <E extends Comparable <E> > {
	
	/**
	 * Adds an item to this binary search tree.
	 * @param item the item to be added
	 */
	void insert( E item );
	/**
	 * Removes an item from this binary search tree.
	 * If item is not in the tree, the structure is unchanged.
	 * @param item the item to be removed
	 */
	void remove ( E item );
	/**
	 * Determines if an item is located in this binary search tree.
	 * @param item the item to be located
	 * @return true if the item is in the tree, false otherwise
	 */
	boolean contains ( E item );
}