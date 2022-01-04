package bus;

public class NegativeNumberException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private final static String message = "Number can't be negative";
	
	public NegativeNumberException()
	{
		super(message);		
	}
	public NegativeNumberException(String newMessage)
	{
		super(newMessage);		
	}

}
