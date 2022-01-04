package bus;

public class InvalidInputException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private final static String message = "Invalid type of input!" ;
	
	public InvalidInputException()
	{
		super(message);		
	}
	public InvalidInputException(String newMessage)
	{
		super(newMessage);		
	}
}
