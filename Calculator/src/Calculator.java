import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class is the back-end version of a calculator.
 * It takes two command line arguments containing two files;
 * the input file containing expressions to be evaluated (infix)
 * and and output file where the results will be written into.
 * 
 * @author Dana Kirsanov
 * @version 11/18/2015
 */
public class Calculator {
	
	/**
	 * The main method checks the two command line arguments 
	 * and checks that the files are valid.
	 * It parses through the file, and using the ExpressionTools
	 * class converts each line to a infix expression and evaluates it.
	 * It prints the result for each expression to the second
	 * command line argument file.
	 * The original expression must be an infix expression, with only 
	 * positive integers, parenthesis, and "+", "-", "*" or "/" operands.
	 * If an expression is in valid the method will print "INVALID".
	 * If the expression attempts to divide by zero the method will
	 * print "UNDEFINED".
	 * 
	 * @param args the two file names. First to be read from, second
	 * to be written to.
	 * @throws FileNotFoundException if file does not exist
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		if (args.length < 1) { 
			System.err.println("Input and output file names missing.");
			System.exit(0);
		}
		if (args.length < 2){
			System.err.println("Only one file name. Missing either output or input file names.");
			System.exit(0);
		}
		File inputFile = new File(args[0]);
		//displays error if file is not found or can't be read from
		if (!inputFile.exists() || !inputFile.canRead()){
			System.err.printf("Error: file %s does not exist.\n", inputFile);
			System.exit(0);
		}

		Scanner input = new Scanner(inputFile);
		try{
			File outputFile = new File(args[1]); 
			PrintWriter output = new PrintWriter(outputFile); //create output file

			while (input.hasNext()){
				try{
					String postfix = ExpressionTools.infixToPostfix(input.nextLine());
					int result = ExpressionTools.EvaluatePostfix(postfix);
					output.println(result);
				} catch(PostFixException e){
					output.println("INVALID");
				} catch(ArithmeticException e){ //catches when expression divides by zero
					output.println("UNDEFINED");
				}
			}
			output.close();
			
		} catch (IOException e){
			System.err.println("Error: could not create file " + args[1]);

		} finally{
			input.close();
		}
	}
}
