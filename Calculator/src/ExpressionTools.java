import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This is a tool-kit class that provides methods 
 * that convert a infix expression to postfix, 
 * and that evaluates a postfix expression.
 * 
 * @author Dana Kirsanov & Joanna Klukowska
 * @version 11/18/2015
 */
public final class ExpressionTools {

	/**
	 * Doesn't let instantiate the class.
	 */
	private ExpressionTools() {}
	
	/**
	 * Takes a infix expression and converts it to the corresponding
	 * postfix expression.
	 * 
	 * @param infix a string with the infix expression, where each character 
	 * is separated by a space. Only "+", "-", "/". "*" operator,
	 * integers, and parenthesis are allowed.
	 * @return a string with the postfix expression
	 * @throws PostFixException if the expression is not valid
	 */
	public static String infixToPostfix(String infix) throws PostFixException{
		String postfix = "";
		MyStack<String> operator = new MyStack<String>();
		Scanner tokenizer = new Scanner(infix);

		if(infix == null)
			throw new PostFixException("No expression.");

		if(infix.equals(""))
			throw new PostFixException("No expression.");

		//check matching parenthesis before advancing
		if (!checkParenthesis(infix))
			throw new PostFixException("Parenthesis do not match.");

		while(tokenizer.hasNext()){
			
			String token = tokenizer.next(); 
			
			if(isInteger(token)){ //if operand
				postfix = postfix.concat(token + " "); //add to postfix expression
				
			} else if(token.equals("(")){
				operator.push(token);
				
			} else  if(isOperator(token)){ //if token is operator
				if(!operator.empty()){
					//while stack is not empty 
					//and the object on top has a precedence higher or equal
					while(!operator.empty() && (getPrecedence(operator.peek()) >= getPrecedence(token))){
						String valuePop = operator.pop();
						postfix = postfix.concat(valuePop + " "); //add to postfix
					}
				}
				operator.push(token);
				
			} else if(token.equals(")")){
				while(!operator.empty()){
					if(!operator.peek().equals("(")){
						String valuePop = operator.pop();
						postfix = postfix.concat(valuePop + " ");
					} else{
						operator.pop();
						break;
					}
				}
				
			} else //if it encounter illegal character that is not operator, integer or parenthesis
				throw new PostFixException("Illegal character in the expression."); 
		}
		while(!operator.empty()){
			String valuePop = operator.pop();
			postfix = postfix.concat(valuePop + " ");
		}
		
		tokenizer.close();
		return postfix;
	}

	/**
	 * Determine if the string passed as a parameter contains
	 * matching parenthesis or not.
	 * @param fileToParse 
	 *    String that contains text to be tested for the
	 *    matching parenthesis.
	 * @return
	 *    true, if the fileToParse contains only matching parenthesis.
	 *    false, if it contains unmatched parenthesis.
	 */
	private static boolean checkParenthesis(String infix) {
		//stack to hold unmatched open parenthesis
		MyStack<Character> unmatchedOpenParenthesis = new MyStack<Character>();
		Character currentChar;
		for (int i = 0; i < infix.length(); i++ ) {
			currentChar = infix.charAt(i);

			//if the current character is an opening
			//parenthesis, add it to the stack
			if (currentChar == '(')
				unmatchedOpenParenthesis.push(currentChar);

			//if it is a closing parenthesis, check if the parenthesis
			//at the top of the stack matches it;
			//if it does, keep going, otherwise string is not valid
			if (currentChar == ')') {
				try{
					Character openChar = unmatchedOpenParenthesis.pop();
					if(!(openChar == '(' && currentChar == ')'))
						return false;
				} catch(EmptyStackException e){ //if stack is empty, mean there's no openChar
					return false;
				} finally{
					if (currentChar == null)
						return false;
				}
			}
			//if the character is anything else, ignore it
		}
		//if the stack is empty at this point, the string is valid,
		//otherwise it has unmatched parenthesis
		if ( unmatchedOpenParenthesis.empty()) 
			return true;
		else
			return false;
	}


	/**
	 * Determines if the content of a string is an integer.
	 * 
	 * @param s a string to check if is an integer or not
	 * @return true if the string is an integer, false otherwise
	 */
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if the content of a string is an operator.
	 * Note that the only valid operators for this method
	 * are "+", "-", "/" and "*".
	 * 
	 * @param s a string to check if is an operator or not
	 * @return true if the string is an operator, false otherwise
	 */
	private static boolean isOperator(String s){
		if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
			return true;
		else
			return false;
	}
	
	/**
	 * Checks the precedence of an operator. 
	 * i.e. if it is * or / then it has a higher precedence
	 * then + or -. Note, these are the only operators allowed.
	 * 
	 * @param operator a string containing the operator to be checked
	 * @return 1 for low precedence, if operator is "-" or "+"
	 * 2 for high precedence, if the operator is "*" or "/".
	 * 0 if the operator is "(" or ")" (precedence doesn't count).
	 * @throws PostFixException if operator is not "-", "+". "/",
	 * "*", "(" or ")".
	 */
	private static int getPrecedence(String operator) throws PostFixException {
		if(operator.equals("+") || operator.equals("-"))
			return 1;
		else if(operator.equals("*") || operator.equals("/"))
			return 2;
		else if(operator.equals("(") || operator.equals(")"))
			return 0;

		throw new PostFixException("Invalid operaor");
	}    
	
	/**
	 * Evaluates a postfix expression and returns its value.
	 * Note that this method only performs integer operations.
	 * This method is mostly obtained from the class notes.
	 * 
	 * @param postfix the postfix expression to be evaluated
	 * @return the integer that the expression evaluates to
	 * @throws PostFixException if the postfix expression is not valid.
	 */
	public static int EvaluatePostfix(String postfix) throws PostFixException{
		MyStack<Integer> stack = new MyStack<Integer>();

		int value;
		String operator;

		int operand1;
		int operand2;

		int result = 0;

		Scanner tokenizer = new Scanner(postfix);

		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextInt()) {
				// Process operand.
				value = tokenizer.nextInt();

				stack.push(value);
			} else {
				// Process operator.
				operator = tokenizer.next();

				// Obtain second operand from stack.
				if (stack.empty()) {
					tokenizer.close();
					throw new PostFixException("Not enough operands.");
				}
				operand2 = stack.peek();
				stack.pop();

				// Obtain first operand from stack.
				if (stack.empty()) {
					tokenizer.close();
					throw new PostFixException("Not enough operands.");
				}
				operand1 = stack.peek();
				stack.pop();

				// Perform operation.
				if (operator.equals("/"))
					result = operand1 / operand2;
				else if (operator.equals("*"))
					result = operand1 * operand2;
				else if (operator.equals("+"))
					result = operand1 + operand2;
				else if (operator.equals("-"))
					result = operand1 - operand2;
				else {
					tokenizer.close();
					throw new PostFixException("Illegal symbol: " + operator);
				}

				// Push result of operation onto stack.
				stack.push(result);
			}
		}

		tokenizer.close();

		// Obtain final result from stack.
		if (stack.empty())
			throw new PostFixException("Not enough operands.");
		result = stack.peek();
		stack.pop();

		// Stack should now be empty.
		if (!stack.empty())
			throw new PostFixException("Too many operands.");

		// Return the final.
		return result;
	}
}

