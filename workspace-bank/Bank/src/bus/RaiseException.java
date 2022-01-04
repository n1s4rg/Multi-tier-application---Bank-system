package bus;

public class RaiseException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private final static String message = "Raise Exception" ;
	
	public RaiseException()
	{
		super(message);		
	}
	
	public RaiseException(String newMessage)
	{
		super(newMessage);		
	}
	
	
}
