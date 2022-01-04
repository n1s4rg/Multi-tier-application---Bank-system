package bus;

public class InputMismatchException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private final static String message = "Invalid type of input!" ;
	
	public InputMismatchException()
	{
		super(message);		
	}
	public InputMismatchException(String newMessage)
	{
		super(newMessage);		
	}

}
