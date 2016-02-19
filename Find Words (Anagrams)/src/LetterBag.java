
package proj2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The class represents a collection of letters.
 * It provides a way of creating sets of words from the stored letters 
 * given a dictionary.  
 * @author Joanna Klukowska
 */
public class LetterBag implements LetterBagInterface{
	//the letters in the object
	private ArrayList<Character> lettersInBag;
	
	//stores reference to a dictionary to avoid passing it
	//between recursive calls.
	private Dictionary dict; 
	
	/**
	 * Construct a LetterBag object given a String containing letters. 
	 * @param letters letters to be put into the LetterBag object
	 * @throws IllegalArgumentException when the letters String contains 
	 * non-letter characters, or its length is smaller than 3 or greater than 10
	 * an exception is thrown
	 */
	public LetterBag(String letters) throws IllegalArgumentException{
		
		checkValidity(letters); 
		
		lettersInBag = new ArrayList<Character> ();
		for (int i = 0; i < letters.length(); i++)
			lettersInBag.add( letters.charAt(i) );
	}
	
	
	
	/*
	 * Tests given set of letters for validity: only Strings containing letters
	 * and of lengths between 3 and 10 are valid. 
	 * @param letters letters to be put into the LetterBag object
	 * @throws IllegalArgumentException when the letters String contains 
	 * non-letter characters, or its length is smaller than 3 or greater than 10
	 * an exception is thrown
	 */
	private void checkValidity (String  letters) throws IllegalArgumentException {
		if (null == letters )
			throw new IllegalArgumentException (
					"Error creating LetterBag object: Null argument encountered.");
		if (letters.length() < 3 || letters.length() > 1000)
			throw new IllegalArgumentException (
					"Error creating LetterBag object: Invalid number of characters.");
		if (letters.contains(" "))
			throw new IllegalArgumentException (
					"Error creating LetterBag object: Invalid character found.");
		for (int i = 0; i < letters.length(); i++) 
			if (!Character.isLetter(letters.charAt(i)))
				throw new IllegalArgumentException (
						"Error creating LetterBag object: Invalid character found.");
	}
	
	/**
	 * Constructs all words from the letters in the current object that are 
	 * listed in the given dictionary. 
	 * @param dict  the dictionary to use in the search
	 * @return  ArrayList object containing all possible words, or null if either
	 * passed dictionary is null, or there are no words that can be created. 
	 */
	public ArrayList<String> getAllWords (Dictionary dict) {
		if ( null == dict ) return null;
		this.dict = dict;
		//generate the results
		ArrayList<String> words = new ArrayList<String> ();
		StringBuffer prefix = new StringBuffer ();
		getAllWordsRecursive ( lettersInBag, prefix, words );
		//remove duplicates and sort the results 
		cleanUpResults(words);
		return words;
	}
	
	/*
	 * Actual recursive method (using backtracking) that constructs the words. 
	 * @param possibleLetters  remaining letters that can be added to the prefix
	 * @param prefix  prefix constructed so far
	 * @param words   collection of completed words that have been already discovered 
	 */
	private void getAllWordsRecursive ( 
			ArrayList<Character> possibleLetters, 
			StringBuffer prefix,
			ArrayList<String> words
			) 
	{			
		//add the current prefix to the list of words 
		if (possibleLetters.size() == 0 && dict.findWord(prefix.toString())) {
			words.add(prefix.toString());
		}
		else
			for (int i = 0; i < possibleLetters.size(); i++ ) {
				//add next character to the current prefix 
				prefix.append(possibleLetters.get(i));
				//if the new prefix is in the dictionary, make a recursive call 
				if ( dict.findPrefix(prefix.toString() ) ) {
					ArrayList <Character> remainingLetters = new ArrayList<Character> (possibleLetters);
					remainingLetters.remove(i);
					getAllWordsRecursive( remainingLetters, prefix, words );
					prefix.deleteCharAt(prefix.length()-1);
					
				}
				else {
					prefix.deleteCharAt(prefix.length()-1);
				}
			}
	}
	
	/* 
	 * Cleans up the results computed by getAllWordsRecursive by sorting
	 * them and removing all repeated words.
	 * @param words  collection of words computed by getAllWordsRecursive
	 */
	private void cleanUpResults(ArrayList<String> words) {
		// sort the results 
		Collections.sort(words);
		//remove duplicates
		int i = 1;
		while (i < words.size()) {
			if (words.get(i).equals(words.get(i - 1)))
				words.remove(i);
			else
				i++;
		}

	}
	
}
