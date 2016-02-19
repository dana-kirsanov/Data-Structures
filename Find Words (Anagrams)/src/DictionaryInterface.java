package proj2;

/* *
 * This is an interface for representing a dictionary,
 * which is implemented in the Dictionary class.
 * It declares methods that check if a word or a prefix of a word
 * exist in the dictionary.
 * @author Joanna Klukowska
 */

public interface DictionaryInterface {
	/**
	 * This method determines if a given word 
	 * is in this Dictionary.
	 * @param word the word to be checked
	 * @return true of the word is in this Dictionary,
	 * false otherwise
	 */
	boolean findWord ( String word );

	/**
	 * This method determines if a given
	 * prefix is a prefix of a word that exists in
	 * this Dictionary.
	 * @param prefix the prefix to be checked  
	 * @return true if the prefix is in this Dictionary,
	 * false otherwise 
	 */
	boolean findPrefix ( String prefix ); 
}
