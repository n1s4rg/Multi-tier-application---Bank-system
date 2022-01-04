package bus;

public class NotNullException extends Exception {

	private static final long serialVersionUID = 1L;

	private final static String message = "Value can't be empty" ;
	
	public NotNullException()
	{
		super(message);		
	}
	public NotNullException(String newMessage)
	{
		super(newMessage);		
	}
}
