package proj2;

/**
 * BSTNode class is used to represent nodes in a binary search tree (BST).
 * It contains a data item that has to implement Comparable interface
 * and references to left and right subtrees. 
 * 
 * @author Joanna Klukowska and Dana Kirsanov
 *
 * @param <E> 
 *    a reference type that implements Comparable<E> interface 
 */
public class BSTNode <E extends Comparable <E>> 
implements Comparable <BSTNode <E> > {

	//reference to the left subtree 
	private BSTNode <E> left;
	//reference to the right subtree
	private BSTNode <E> right;
	//data item stored in the node
	private E data;

	/**
	 * Constructs a BSTNode initializing the data part 
	 * according to the parameter and setting both 
	 * references to subtrees to null.
	 * @param data
	 *    data to be stored in the node
	 */
	public BSTNode(E data) {
		this.data = data;
		left = null;
		right = null;
	}

	/**
	 * Constructs a BSTNode initializing the data part
	 * and the subtree references according to the parameters.
	 * @param data
	 *    data to be stored in the node
	 * @param left
	 *    reference to the left subtree
	 * @param right
	 *    reference to the right subtree
	 */
	public BSTNode( E data, BSTNode<E> left, BSTNode<E> right) {
		this.left = left;
		this.right = right;
		this.data = data;
	}

	/**
	 * Left subtree getter. 
	 * @return 
	 *    reference to the left subtree of a node
	 */
	public BSTNode<E> getLeft() {
		return left;
	}

	/**
	 * Changes the reference to the left subtree to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new left subtree of the node.
	 */
	public void setLeft(BSTNode<E> left) {
		this.left = left;
	}

	/**
	 * Right subtree getter. 
	 * @return 
	 *    reference to the right subtree of a node
	 */
	public BSTNode<E> getRight() {
		return right;
	}

	/**
	 * Changes the reference to the right subtree to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new right subtree of the node.
	 */
	public void setRight(BSTNode<E> right) {
		this.right = right;
	}

	/**
	 * Returns a reference to the data stored in the node. 
	 * @return 
	 *    reference to the data stored in the node
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Changes the data stored in the node to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new data of the node
	 */
	public void setData(E data) {
		this.data = data;
	}

	/* Compares the data of the node with the data of another node.
	 * @param other node to be compared to
	 * @return a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, 
	 * or greater than the specified object
	 */
	@Override
	public int compareTo(BSTNode<E> other) {
		return this.data.compareTo(other.data);
	} 


}