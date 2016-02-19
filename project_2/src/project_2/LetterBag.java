package project_2;

import java.util.ArrayList;
import java.util.Collections;
/* *
 * This class represents the letters entered by the user. 
 * This class is responsible for creation of all the different words which are
 * valid anagrams of the word entered by the user. 
 * It uses the Dictionary class to accomplish this via the getAllWords method.
 * @author Dana Kirsanov
 * @version 10/9/2015
 */
public class LetterBag implements LetterBagInterface{
	private String word = null;
	private ArrayList<String> validWords = new ArrayList<String>();

	/* *
	 * The constructor take the word (entered by user) to be used
	 * later to get all valid anagrams.
	 * @param word the word entered by the user
	 */
	public LetterBag(String word) {
		this.word = word;
	}

	/* *
	*This method determines the list of words that can be
	*created from a given LetterBag object that are present
	*in the provided Dictionary object dict.
	*It calls the limitDict method to create the new shorter dictionary in
	*the Dictionary class, to be used for checking words/prefixes.
	*It calls a helper method that takes extra parameters, and
	*returns an ArrayList of valid words which is modified by the helper method.
	*@param dict the Dictionary object to be used.
	*@return a list of valid words in alphabetical order.
	 */
	public ArrayList<String> getAllWords(Dictionary dict) {
		dict.limitDict(word); //makes new dict with only words that are the same length
		getAllWords(dict, word, "");
		return validWords; //returns ArrayList of valid anagrams
	}

	/* *
	 *This method determines the list of words that can be
	 *created from a given LetterBag object that are present
	 *in the provided Dictionary object dict.
	 *It uses backtracking to create all possible anagrams, while 
	 *checking every new prefix to see if it exists in the dictionary which
	 *determines if the method follows that character path or not.
	 *It stores all the valid words in an ArrayList and uses selection sort
	 *to sort it alphabetically. 
	 *@param dict the Dictionary object to be used.
	 *@param word the characters left to check and rearrange.
	 *@param p the prefix that the rest of the characters will be added after.
	 */
	private void getAllWords(Dictionary dict, String word, String p) {

		if(word.length() <= 1){ //base case
			//System.out.println(p + word);
			if(dict.findWord(p + word)){ //if word is in dict, adds to ArrayList
				if(!validWords.contains(p + word)){ //prevents duplicates
					validWords.add(p + word);
				}	
			} 
		} else{
			if(dict.findPrefix(p)){ //check if prefix exists
				for(int i=0; i < word.length(); i++){
					String s = word.substring(i, i+1); //letter to add to p (prefix)
					String before = word.substring(0, i); //letters before s
					String after = word.substring(i+1); //letters after s
					getAllWords(dict, before + after, p + s);
				}
			}
		}
		//selection sort to sort list alphabetically:
		for(int i = 0; i < validWords.size() - 1; i++){ 
			int index = 0;
			for(int j = 1; j < validWords.size() - i; j++){
				if(validWords.get(j).compareTo(validWords.get(index)) > 0){
					index = j;
				}
			}
			Collections.swap(validWords, index, validWords.size() - i - 1);
		}
	}
}
