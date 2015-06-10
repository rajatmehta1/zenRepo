package zenmobile.utils;

/*
 * Wrapper over plain exception to show a custom error message to the user 
 * when needed.
 * @author Rajat
 */
public class ApplicationException extends Exception{

	private String message;
	
	public ApplicationException(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}
	
}
