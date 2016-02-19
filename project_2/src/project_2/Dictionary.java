package project_2;

import java.util.ArrayList;
/* *
* This class represents the collection of words read in from the input file 
* (i.e., the dictionary used by the program). 
* This class is responsible for performing queries in the dictionary.
* It has methods that check if a word or a prefix of a word
* exist in the dictionary.
* It implements the DictionaryInterface.
* @author Dana Kirsanov
* @version 10/9/2015
*/
public class Dictionary implements DictionaryInterface{
	private ArrayList<String> dict = new ArrayList<String>();
	private ArrayList<String> dict2 = new ArrayList<String>(); //modified dictionary
	
	/* *
	* Constructor take an ArrayList of Strings and declares dict.
	* @param dictArray and ArrayList of all words of the dictionary
	*/
	public Dictionary(ArrayList<String> dictArray) {
		dict = dictArray;
	}
	
	/* *
	* This method makes a new, shorter, modified dictionary with only
	* words that are the same length as the word specified in the parameter.
	* This the findPrefix and findWord methods look through a shorter ArrayList
	* making the binary search a bit faster.
	* This method MUST be called BEFORE calling the findPrefix or
	* findWord methods, using the word that is intended to be checked.
	* @param word the word which will decide the length of the words in the modified dictionary
	*/
	public void limitDict(String word){
		for(String s: dict){
			if (s.length() == word.length()){
				dict2.add(s); //adds word to modified dict if it's the right length
			}
		}
	}
	
	/* *
	* This method calls a helper method which
	* determines if a given word is in the Dictionary. 
	* @param word the word to be checked for in the dictionary.
	* @return true if the word is in the dictionary, false otherwise.
	*/
	public boolean findWord(String word) {
		return findWord(word, 0, this.dict2.size() - 1);
		
	}
	
	/* *
	* The helper method called for findWord that takes extra parameters left & right.
	* This method determines if a given word is in the Dictionary, 
	* implementing a recursive binary search.
	* @param word the word to be checked for in the dictionary.
	* @param left the left index of the part of the array to be checked.
	* @param right the right index of the part of the array to be checked.
	* @return true if the word is in the dictionary, false otherwise.
	*/
	private boolean findWord(String word, int left, int right){
		if(dict2.size() == 0){ //if no words in (modified) dictionary 
			return false;
		}
		if(right < left){  //base case => means word is not in dictionary
			return false;				
		}
		
		int mid = (left + right)/2; 
		
		if(this.dict2.get(mid).equals(word)){ //means the word was found
			return true;
		} else if(this.dict2.get(mid).compareTo(word) < 0){; 
			return findWord(word, mid + 1, right);  //narrows down the search
		} else{										
			return findWord(word, left, mid - 1);
		}
		
	}
	
	/* *
	* This method calls a helper method which 
	* determines if a given prefix is in the Dictionary, 
	* i.e. if any words in the dictionary start with that prefix.
	* @param prefix the prefix to be checked.
	* @return true if the prefix is in the Dictionary, false if not.
	
	*/
	public boolean findPrefix(String prefix) {
		return findPrefix(prefix, 0, this.dict2.size() - 1); //call helper method
	}
	
	/* *
	* This is the helper method which determines 
	* if a given prefix is in the Dictionary, 
	* i.e. if any words in the dictionary start with that prefix.
	* It takes the extra parameters left and right, which represent
	* the left and right indexes.
	* It implements a recursive binary search to check for the prefix.
	* @param prefix the prefix to be checked.
	* @param left the left index of the part of the array to be checked.
	* @param right the right index of the part of the array to be checked.
	* @return true if the prefix is in the Dictionary, false if not.
	*/
	private boolean findPrefix(String prefix, int left, int right){
		
		if(dict2.size() == 0){ //if no words in (modified) dictionary 
			return false;
		};
		if(right < left){  //base case prefix not found
			return false;				
		};
		
		int mid = (left + right)/2;
		
		if(dict2.get(mid).startsWith(prefix)){
			//prefix found
			return true;
		} else if(dict2.get(mid).substring(0, prefix.length()).compareTo(prefix) < 0){
			//Keeps searching my narrowing down left/right boundary
			return findPrefix(prefix, mid + 1, right);
		} else{
			return findPrefix(prefix, left, mid - 1);
		}
	}

}
