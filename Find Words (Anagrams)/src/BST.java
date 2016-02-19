package proj2;

/**
 * This class represent a Binary Search Tree.
 * It uses the BSTNode class to represents the nodes in the tree.
 * It provides methods for inserting and removing elements,
 * and checking if the tree contains a specific element.
 * 
 * @author Joanna Klukowska and Dana Kirsanov
 *
 */
public class BST<E extends Comparable<E>> implements BSTInterface<E>{

	protected BSTNode<E> root;
	protected int numOfNodes; //keeps track of the size

	/**
	 * Creates an empty Binary Search Tree (no objects).
	 */
	public BST(){
		this.root = null;
		numOfNodes = 0;
	}

	/* *
	 * Adds an item to this binary search tree.
	 * @param item the item to be added
	 */
	public void insert( E item ) {
		if(item == null)
			return;
		if(root == null)
			root = new BSTNode<E>(item);
		else{
			BSTNode<E> parent = null;
			BSTNode<E> current = root;
			while (current != null){
				if(item.compareTo(current.getData()) < 0){
					parent = current;
					current = current.getLeft();
				} else {
					parent = current;
					current = current.getRight();
				}
			}
			if (item.compareTo(parent.getData()) < 0)
				parent.setLeft(new BSTNode<E>(item));
			else
				parent.setRight(new BSTNode<E>(item));
		}
		numOfNodes++;
	}

	/* *
	 * Removes an item from this binary search tree.
	 * If item is not in the tree, the structure is unchanged.
	 * @param item the item to be removed
	 */
	public void remove ( E item ) {
		if (item == null)
			return;
		BSTNode<E> parent = null;
		BSTNode<E> current = root;
		while (current != null) {
			if (item.compareTo(current.getData()) < 0) {
				parent = current;
				current = current.getLeft();
			} else if (item.compareTo(current.getData()) > 0) {
				parent = current;
				current = current.getRight();
			} else
				break; // Element is in the tree pointed at by current
		}

		if (current == null)
			return; // Element is not in the tree

		//current has no left children
		if (current.getLeft() == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.getRight();
			} else {
				if (item.compareTo(parent.getData()) < 0)
					parent.setLeft(current.getRight());
				else
					parent.setRight(current.getRight());
			}
		} else {
			// current node has a left child
			BSTNode<E> parentOfRightMost = current;
			BSTNode<E> rightMost = current.getLeft();

			while (rightMost.getRight() != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.getRight(); 
			}

			// Replace the item in current by the item in rightMost
			current.setData(rightMost.getData());

			// Eliminate rightmost node
			if (parentOfRightMost.getRight() == rightMost)
				parentOfRightMost.setRight(rightMost.getLeft());
			else
				// Special case: parentOfRightMost == current
				parentOfRightMost.setLeft(rightMost.getLeft());
		}
		numOfNodes--;
	}
	
	/* *
	 * Determines if an item is located in this binary search tree.
	 * @param item the item to be located
	 * @return true if the item is in the tree, false otherwise
	 */
	public boolean contains ( E item ) {
		return contains(item, root);
	}
	
	/* *
	 * The actual recursive implementation of the contains method.
	 * @param item  the item to be located
	 * @param current  the current BSTNode
	 * @return true if the item is in the tree, false otherwise
	 */
	private boolean contains (E item, BSTNode<E> current){
		if (current == null) //item not there
			return false; 
		else if (item.compareTo(current.getData() ) < 0)
			return contains(item, current.getLeft());
		else if (item.compareTo(current.getData() ) > 0)
			return contains(item, current.getRight());
		else 
			return true; 
	}
}
