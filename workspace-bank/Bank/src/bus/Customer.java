package bus;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.DataCollection;

public class Customer implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long identificationNum;
	
	private Person name;
	
	private List<Account> listOfAccounts = new ArrayList<Account>();
	
	private long pinNum;
	private CheckingAccount checkingAccount;
	
	public Customer() throws InvalidInputException, RaiseException {
		super();
		this.setIdentificationNum(Bank.uniqueCustomerIdNum());
		this.setName(new Person());
		this.setPinNum(0);
		this.setCheckingAccount(new CheckingAccount());
	}
	
	public Customer(Person name, long pinNum, CheckingAccount checkingAccount) throws InvalidInputException {
		super();
		this.setIdentificationNum(Bank.uniqueCustomerIdNum());
		this.setName(name);
		this.setPinNum(pinNum);
		this.setCheckingAccount(checkingAccount);
	}

	public long getIdentificationNum() {
		return identificationNum;
	}
	public void setIdentificationNum(long identificationNum) {
		this.identificationNum = identificationNum;
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
	
	
	
	//public services
	public static boolean add(Customer c) throws SQLException {
		return DataCollection.addCustomer(c);
	}
	
	public static List<Customer> getAll() throws SQLException, InvalidInputException, RaiseException{
		return DataCollection.getAllCustomers();
	}
	
	public static Customer getCustomerById(long custId) throws SQLException, InvalidInputException, RaiseException {
		return DataCollection.getCustomer(custId);
	}
	
	public static boolean delete(long custId) throws SQLException {
		return DataCollection.deleteCustomer(custId);
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Identification Number :"+this.identificationNum+", Name: "+this.name.toString();
	}
	
}
