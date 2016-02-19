import java.util.EmptyStackException;

/**
 * This class represents a reference-based stack 
 * (last-in-first-out or LIFO) of objects.
 * The constructor creates an empty stack and the push() and pop()
 * methods are used to add and remove objects from the stack.
 * 
 * @author Dana Kirsanov
 * @version 11/18/2015
 */
public class MyStack<E> {
	Node<E> head;
	int size;
	
	/**
     * Creates an empty Stack.
     */
    public MyStack() {
    	head = null;
    	size = 0;
    }
    
    /**
     * Pushes an item onto the top of this stack. 
     * @param   item the item to be pushed onto this stack.
     * @return  the item pushed.
     */
    public E push(E item) {
    	Node <E> n = new Node<E>(item);
        if (head == null){
        	head = n;
        } else{
        	n.setNext(head);
        	head = n;
        }
        size ++;
        return item;
    }
    
    /**
     * Removes the object at the top of this stack 
     * and returns that object as the value of this function.
     * @return  The object at the top of this stack.
     * @throws  EmptyStackException  if this stack is empty.
     */
    public E pop() {
        if (this.empty()){
            throw new EmptyStackException();
        	//return null;
        }
        E data = head.getData();
        head = head.getNext();
        size--;
        return data;
    }
    
    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack
     * @throws  EmptyStackException  if this stack is empty.
     */
    public E peek() {
    	if (size == 0)
            throw new EmptyStackException();
        return head.getData();
    }
    
    /**
     * Tests if the stack is empty.
     *
     * @return  true if the stack contains contains no objects; false otherwise.
     */
    public boolean empty() {
        if (size == 0)
        	return true;
        return false;
    }
    
    /**
     * Returns the 1-based position where an object is on this stack.
     * If the object o occurs as an item in this stack, this
     * method returns the distance from the top of the stack of the
     * occurrence nearest the top of the stack; the topmost item on the
     * stack is considered to be at distance 1. 
     *
     * @param   o the desired object.
     * @return  the 1-based position from the top of the stack where
     *          the object is located; the return value -1
     *          indicates that the object is not on the stack.
     */
    public int search(Object o) {
    	int count = 1;
    	Node<E> current = head;
    	//if(o == null)
    	//	return -1;
    	while(current != null){
    		if(current.getData().getClass() == o.getClass()){ //checks if same class
    			if(current.getData().equals(o))				// if yes checks if they're equal
        			return count;
    		}
    		current = current.getNext();
    		count++;
    	}
    	return -1;
    }
    
    /*public void toStrings() { 
    	Node<E> current = head; 
    	while(current != null){
    		System.out.println(current.getData());
    		current = current.getNext();
    	}
    	
    }*/
 
}


