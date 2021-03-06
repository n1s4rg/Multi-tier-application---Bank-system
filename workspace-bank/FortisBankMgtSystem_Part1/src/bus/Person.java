package bus;

import java.io.Serializable;

public class Person implements Serializable {
	private String firstName;
	private String lastName;
	
	public Person() {
		super();
		this.firstName = "";
		this.lastName = "";
	}
	
	public Person(String firstName, String lastName) throws InvalidInputException {
		super();
		
		Validator.isAlphabetic(firstName);
		Validator.isAlphabetic(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws InvalidInputException {
		Validator.isAlphabetic(firstName);
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws InvalidInputException {
		Validator.isAlphabetic(lastName);
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.firstName+" "+this.lastName;
	}
	
}
