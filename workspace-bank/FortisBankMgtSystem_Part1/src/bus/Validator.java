package bus;

public class Validator {
	
	public static void isAlphabetic(String value) throws InvalidInputException
	{
		for(int i = 0 ; i != value.length() ; i++)
		{
			if(!Character.isAlphabetic(value.charAt(i)))
			{
				throw new InvalidInputException("Only alphabets allowed!");			
			}		
		}		
	}
	
	public static void isPositive(double value) throws InvalidInputException
	{
		if(value <= 0)
		{			
			throw new InvalidInputException("Value can't be negative!");	
		}
		
	}
	
	//Polymorphism
	public static void isPositive(int value) throws InvalidInputException
	{
		if(value <= 0)
		{			
			throw new InvalidInputException("Value can't be negative!");	
		}
		
	}
	//Polymorphism
		public static void isPositive(long value) throws InvalidInputException
		{
			if(value <= 0)
			{			
				throw new InvalidInputException("Number can't be negative!");	
			}
			
		}
}
