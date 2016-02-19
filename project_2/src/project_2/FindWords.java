package project_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/* *
* This class takes a word inputed by the user and a
* txt dictionary file in the command line argument and prints
* all anagrams of the word that exist in the dictionary.
* @author Dana Kirsanov
* @version 10/9/2015
*/
public class FindWords {
	/* *
	* The main method is responsible for parsing the command line argument, 
	* reading the input file, reading the user input, 
	* creating the Dictionary and LetterBag objects 
	* and then using them to display the results.
	* @param args the dictionary to be used (as a command line argument)
	* @throws FileNotFoundException when the file name is missing or incorrect
	*/
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length < 1) { //checks a file name was entered
			System.err.println("File name missing.");
			System.exit(0);
		}
		String inputDict = args[0];

		File dictName = new File(args[0]);

		//displays error if file is not found or can't be read from
		if (!dictName.exists() || !dictName.canRead()){
			System.err.printf("Error: file %s does not exist.\n", dictName);
			System.exit(0);
		}

		//reads file and creates ArrayList of words
		Scanner fileInput = new Scanner(new File(inputDict));
		ArrayList<String> dictArray = new ArrayList<String>();
		while (fileInput.hasNext()){
			dictArray.add(fileInput.next());
		}
		fileInput.close();
		
		Dictionary dict = new Dictionary(dictArray); //create Dictionary

		//User character input:
		Scanner input = new Scanner(System.in);
		System.out.print("Enter string of characters (letters only): ");
		String word =  (input.next()).toLowerCase();
		input.close();
		for(int i = 0; i < word.length(); i++){ //checks if each character is valid
			char c = word.charAt(i); 
			if(!Character.isLetter(c)){
				System.err.println("Error: you entered an invalid character that is not a letter.");
				System.exit(0);
			}
			
		}
	
		LetterBag letterbag = new LetterBag(word); //create LetterBag
		ArrayList <String> result = letterbag.getAllWords(dict);
		if(result.size() == 0){ 
			System.out.println("No Words Found.");
		}else{
			System.out.format("Found %d words:\n", result.size());
			for(String s: result){
				System.out.println(s);
			}
		}
		
	}

}
