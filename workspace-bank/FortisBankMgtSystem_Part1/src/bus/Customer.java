package bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer implements Serializable {
	

	private long identificationNum;
	private Person name;
	
	private List<Account> listOfAccounts = new ArrayList<Account>();
	
	private long pinNum;
	private CheckingAccount checkingAccount;
	
	public Customer() {
		super();
		
		this.identificationNum = Bank.uniqueCustomerIdNum(); 
		this.name = new Person();
		this.pinNum = 0;
		this.checkingAccount = new CheckingAccount();
	}
	
	public Customer(Person name, long pinNum, CheckingAccount checkingAccount) {
		super();
		
		this.identificationNum = Bank.uniqueCustomerIdNum();
		this.name = name;
		this.pinNum = pinNum;
		this.checkingAccount = checkingAccount;
	}
	
	public long getIdentificationNum() {
		return identificationNum;
	}
	
	public Person getName() {
		return name;
	}
	
	public void setName(Person name) {
		this.name = name;
	}
	
	public List<Account> getListOfAccounts() {
		return listOfAccounts;
	}
	
	public long getPinNum() {
		return pinNum;
	}
	public void setPinNum(long pinNum) throws InvalidInputException {
		Validator.isPositive(pinNum);
		this.pinNum = pinNum;
	}
	
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}
	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	
	public void addNewAccount(Account account) {
		this.listOfAccounts.add(account);
	}
	
	public boolean removeAccount(long accountNum) {
		
		for(int i = 0;i<listOfAccounts.size();i++) {
		
			if(accountNum == listOfAccounts.get(i).getAccountNum()) {
				this.listOfAccounts.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Identification Number :"+this.identificationNum+", Name: "+this.name.toString();
	}
}
