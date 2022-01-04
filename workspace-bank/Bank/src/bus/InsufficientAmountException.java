package bus;

public class InsufficientAmountException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private final static String message = "Insufficient amount" ;
	
	public InsufficientAmountException()
	{
		super(message);		
	}
	public InsufficientAmountException(String newMessage)
	{
		super(newMessage);		
	} 

}
