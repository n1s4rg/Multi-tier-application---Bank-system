package bus;

import java.util.Date;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.DataCollection;

public abstract class Account implements Serializable, IOperation  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accountNum;
	private Date openedDate;
	private double extraFee;
	private List<Transactions> listOfTransactions = new ArrayList<Transactions>();
	private double amount;
	private String type;


	public Account() throws InvalidInputException 
	{
		super();
		this.setAccountNum(Bank.uniqueAccountNumber());
		this.setOpenedDate(Calendar.getInstance().getTime());
		this.setExtraFee(0.0);
		this.setAmount(0.0);
		this.setType("");
	}
	
	public Account(double amount, double extraFee) throws InvalidInputException {
		super();
		this.setAccountNum(Bank.uniqueAccountNumber());
		this.setOpenedDate(Calendar.getInstance().getTime());
		this.setExtraFee(extraFee);
		this.setAmount(amount);
	}
	
	public long getAccountNum() {
		return accountNum;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public double getExtraFee() {
		return extraFee;
	}
	public void setExtraFee(double extraFee) throws InvalidInputException {
		Validator.isPositive(extraFee);
		this.extraFee = extraFee;
	}
	public List<Transactions> getListOfTransactions() {
		return listOfTransactions;
	}

	public double getAmount() {
		return amount;
	}
	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public void setAmount(double amount) throws InvalidInputException {
		Validator.isPositive(amount);
		this.amount = amount;
	}
	
	public abstract String viewAccount();
	
	public void addTransaction(Transactions t) {
		this.listOfTransactions.add(t);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//public services
	public static boolean add(long custId, Account a) {
		return DataCollection.addAccount(custId, a);
	}
	
	public static List<Account> getAll(long custId) throws InvalidInputException {
		return DataCollection.getAllCustomerAccounts(custId);
	}
	
	public static Account getAccountByNum(long accNum) throws InvalidInputException {
		return DataCollection.getAccountByNum(accNum);
	}
	
	public static boolean delete(long accNum) {
		return DataCollection.deleteAccount(accNum);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Account ";
		str += "Account Number: "+this.getAccountNum()+", Balance: "+this.getAmount();
		return str;
	}
}
