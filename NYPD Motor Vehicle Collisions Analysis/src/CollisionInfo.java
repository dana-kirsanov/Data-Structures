

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a program that computes some information about the data posted by
 * OpenNYC regarding the collision records on the streets of NYC.  
 * 
 * @author Joanna K.
 *
 */
public class CollisionInfo {

	/**
	 * The main method that starts the program. It is responsible for opening and reading the
	 * input file, creating the CollisionList object and using it compute the 
	 * predetermined results. 
	 * @param args the array should contain the name of the input file as the first element, 
	 * all other elements are ignored 
	 * @throws FileNotFoundException if the input file is corrupted or vanishes during the 
	 * execution of this program 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		final int NUM_OF_ENTRIES = 21; 
		long startTimer, elapsedTime1, elapsedTime2;
		
		
		startTimer = System.nanoTime();
		
		if (args.length < 1) {
			System.err.println("File name missing");
			System.exit(0);
		}
		
		File fileName = new File(args[0]);
		
		if (!fileName.canRead()) {
			System.err.printf("Cannot read from file %s\n.", fileName.getAbsolutePath());
			System.exit(0);
		}
		
		Scanner fin = new Scanner(fileName);
		
		CollisionList list = new CollisionList();
		
		while ( fin.hasNextLine() ) {

			String textLine = fin.nextLine(); 
			ArrayList <String> words = split (textLine ) ;

			if (words.size() != NUM_OF_ENTRIES) {
				continue; //skip lines that are not complete
			}
			list.add(words);
		}
		elapsedTime1 = System.nanoTime() - startTimer; 
		
		startTimer = System.nanoTime();
		//task 1 
		System.out.println("ZIP codes with the largest number of collisions:");
		System.out.println( list.getZipCodesWithMostCollisions( 3 ) );
		
		//task2
		System.out.println("ZIP codes with the fewest number of collisions:");
		System.out.println( list.getZipCodesWithLeastCollisions( 3 ) ); 

		//task 3
		System.out.println("ZIP codes with the most injuries and fatalities (combined):");
		System.out.println( list.getZipCodesWithMostPersonIncidents( 3 ) );
		
		//task 4
		System.out.println("ZIP codes with the most cyclist injuries and fatalities:");
		System.out.println( list.getZipCodesWithMostCyclistIncidents( 3 ) );
		
		//task5:
		System.out.println("Percentage of collisions involving certain vehicle type:");
		System.out.println(list.getVehicleTypeStats());
		
		//task6:
		System.out.println("Fraction of collisions by hour:");
		System.out.println(list.getHourlyStats());

		elapsedTime2 =  System.nanoTime() - startTimer; 

		System.out.println("\n\n============================================\n");
		System.out.printf("Reading and storing data: %,15d nanoseconds\n", elapsedTime1);
		System.out.printf("Computation of results  : %,15d nanoseconds\n", elapsedTime2);
				
		fin.close();
		
		
		
	}


	/**
	 * Splits a given line according to commas (commas within entries are ignored) 
	 * @param textLine line of text to be parsed 
	 * @return an ArrayList object containing all individual entries/tokens
	 * found on the line. 
	 */
	public static ArrayList<String> split (String textLine ) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar;
		boolean insideQuotes = false;
		
		for(int i = 0; i < lineLength; i++ ) {
			nextChar = textLine.charAt(i); 
			//add character to the current entry 
			if ( nextChar != ',' && nextChar != '"' ) {
				nextWord.append( nextChar );
			}
			//double quote found, decide if it is opening or closing one
			else if (nextChar == '"' ) {
				if ( insideQuotes ) {
					insideQuotes = false;
				}
				else {
					insideQuotes = true;
				}
			}
			//found comma inside double quotes, just add it to the string
			else if (nextChar == ',' && insideQuotes) {
				nextWord.append( nextChar );
			}
			//end of the current entry reached, add it to the list of entries
			//and reset the nextWord to empty string
			else if (nextChar == ',' && !insideQuotes) {
				//trim the white space before adding to the list
				entries.add( nextWord.toString().trim() );
				
				nextWord = new StringBuffer();
			}
			
			else {
				System.err.println("This should never be printed.\n");
			}
		}
		//add the last word
		//trim the white space before adding to the list
		entries.add( nextWord.toString().trim() );
				
		return entries; 
	}
	
	
}
