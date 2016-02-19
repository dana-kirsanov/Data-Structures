
package proj2;

import java.util.ArrayList;

/**
 * The class represent a dictionary of words; storing the words
 * in a Binary Search Tree. 
 * It provides a way of searching through the dictionary.
 * 
 * @author Joanna Klukowska and Dana Kirsanov
 *
 */
public class Dictionary extends BST<String> implements DictionaryInterface {

	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary () {
		super();
	}

	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		insertOrder(listOfWords, 0, listOfWords.size() - 1);

	}

	/**
	 * Performs the insertion of the words from the array list
	 * to the Binary Search Tree data structure.
	 * Implements a binary-search like method of inserting elements
	 * so the tree is well balanced, and doesn't result in a linked list.
	 * @param listOfWords  an ArrayList containing the list of words to be added
	 * @param start  the starting index of the specific section of the ArrayList
	 * @param end  the last index of the specific section of the ArrayList
	 */
	private void insertOrder (ArrayList<String> listOfWords, int start, int end){
		if (start > end) //base case
			return;

		int mid = (start + end) / 2;
		insert(listOfWords.get(mid));
		insertOrder(listOfWords, mid + 1, end); //right side
		insertOrder(listOfWords, start, mid - 1); //left side
	}


	/**
	 * Searches in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean findWord ( String word ) {
		String wordLowerCase = word.toLowerCase();
		return contains(wordLowerCase);
	}



	/**
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	public boolean findPrefix (String prefix ) {
		String prefixLowerCase = prefix.toLowerCase();
		return isPrefixInDictionaryRecursive (prefixLowerCase, root);
	}

	/*
	 * The actual method providing recursive implementation of the prefix search
	 * @param prefix  the prefix to look for in this Dictionary object.
	 * @param current  the current BSTNode
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	private boolean isPrefixInDictionaryRecursive(String prefix, BSTNode<String> current) {
		if (current == null)
			return false;

		else if (current.getData().startsWith(prefix)) //checks if prefix
			return true;

		else if(current.getData().compareTo(prefix) > 0) //left branch:
			return isPrefixInDictionaryRecursive(prefix, current.getLeft());

		else if(current.getData().compareTo(prefix) < 0) //right branch:
			return isPrefixInDictionaryRecursive(prefix, current.getRight());

		else //should never happen
			return true;
	}



}
