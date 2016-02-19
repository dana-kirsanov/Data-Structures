/**
 * Thrown to indicate that an expression is invalid.
 * 
 * @author Joanna Klukowska and Dana Kirsanov
 * @version 11/18/2015
 */
public class PostFixException extends Exception{
	
	/**
     * Constructs PostFixException with no detail message.
     */
	public PostFixException(){
		super();
	}
	
	/**
    * Constructs a PostFixException with a
    * specified detail message.
    *
    * @param message the detail message
    */
	public PostFixException(String message){
		super(message);
	}
}
