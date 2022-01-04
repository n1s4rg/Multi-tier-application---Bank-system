package bus;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Account implements Serializable  {

	private long accountNum;
	private Date openedDate;
	private double extraFee;
	private List<Transactions> listOfTransactions = new ArrayList<Transactions>();
	private double amount;

	
	public Account() {
		super();
		this.accountNum = Bank.uniqueAccountNumber(); 
		this.openedDate = Calendar.getInstance().getTime();
		this.extraFee = 0.0;
		this.amount = 0.0;
	}
	
	public Account(double amount, double extraFee) throws InvalidInputException {
		super();
		Validator.isPositive(amount);
		Validator.isPositive(extraFee);
		this.accountNum = Bank.uniqueAccountNumber();
		this.openedDate = Calendar.getInstance().getTime();
		this.extraFee = extraFee;
		this.amount = amount;
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
	
	public void setAmount(double amount) throws InvalidInputException {
		Validator.isPositive(amount);
		this.amount = amount;
	}
	
	public abstract String viewAccount();
	
	public void addTransaction(Transactions t) {
		this.listOfTransactions.add(t);
	}
	
}
