package bus;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	
	public Person() throws RaiseException {
		super();
		this.setFirstName("Undefined");
		this.setLastName("Undefined");
	}
	
	public Person(String firstName, String lastName) throws RaiseException {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);	
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws RaiseException {
		if(Validator.isValidString(firstName)) {
			this.firstName = firstName;
		} else {
			throw new RaiseException("Firstname can't be empty!!!");
		}
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) throws RaiseException {
		if(Validator.isValidString(lastName)) {
			this.lastName = lastName;
		} else {
			throw new RaiseException("Lastname can't be empty!!!");
		}
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.firstName+" "+this.lastName;
	}
	
}
