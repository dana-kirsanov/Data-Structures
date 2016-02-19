package project_2;

import java.util.ArrayList;

/* *
 * This Interface is used by the LetterBag class.
 * It contains the getAllWords method responsible for generating
 * all valid anagrams of the user inputed word.
 * @author Dana Kirsanov & Joanna Klukowska
 */
public interface LetterBagInterface {
	/* *
	*This method determines the list of words that can be
	*created from a given LetterBag object that are present
	*in the provided Dictionary object dict.
	*@param dict the Dictionary object to be used.
	*@return a list of valid words in alphabetical order.
	*/
	ArrayList<String> getAllWords ( Dictionary dict);
}
