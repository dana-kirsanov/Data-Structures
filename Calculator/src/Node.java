
/**
 * Defines a generic node that stores a data item of type E 
 * and a reference to the next node. It is used in the myStack class.
 * @author Dana Kirsanov
 * @version 11/18/2015
 */
public class Node <E> {
	private Node <E> next;
	private E data;
	
	/**
	 * Constructor - creates an empty node.
	 */
	public Node () {
		data = null;
		next = null;
	}
	
	/**
	 * Creates a node with specific data.
	 * @param data data to be stored in the node
	 */
	public Node (E data) {
		if (data != null)
			this.data = data;
	}
	
	/**
	 * Creates a node with specific data and reference to next node.
	 * @param data data item to store in the node
	 * @param next next node
	 */
	public Node (E data, Node<E> next) {
		if (data != null )
			this.data = data;
		if (next != null )
			this.next = next;
	}
	
	/**
	 * The getter method for data
	 * @return data
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Setter method for data
	 * @param data the data to set
	 */
	public void setData(E data) {
		this.data = data;
	}
	
	/**
	 * The getter method for next
	 * @return next
	 */
	public Node<E> getNext() {
		return next;
	}
	
	/**
	 * Setter method for next
	 * @param next the next node to set
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}
}
