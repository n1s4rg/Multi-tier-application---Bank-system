package bus;

import java.util.Date;
import java.util.List;

import data.DataCollection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

public class Transactions implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long transactionNum = 0;
	private long from_account;
	private long to_account;
	private String description;
	private Date transactionDate;
	private EnumTransactionType transactionType;
	private double amount;
	
	public Transactions() {
		super();
		this.setTransactionNum(Bank.uniqueCustomerIdNum());
		this.setDescription("");
		this.setTransactionDate(Calendar.getInstance().getTime());
		this.setTransactionType(EnumTransactionType.UNDEFINED);
		this.setAmount(0.0);
		this.setFrom_account(0);
		this.setTo_account(0);
	}
	
	public Transactions(String description,EnumTransactionType transactionType, 
						double amount,long from_account,long to_account) {
		super();
		this.setTransactionNum(Bank.uniqueCustomerIdNum());
		this.setDescription(description);
		this.setTransactionDate(Calendar.getInstance().getTime());
		this.setTransactionType(transactionType);
		this.setAmount(amount);
		this.setFrom_account(from_account);
		this.setTo_account(to_account);
	}
	
	public long getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(long transactionNum) {
		this.transactionNum = transactionNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public EnumTransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(EnumTransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getFrom_account() {
		return from_account;
	}

	public void setFrom_account(long from_account) {
		this.from_account = from_account;
	}

	public long getTo_account() {
		return to_account;
	}

	public void setTo_account(long to_account) {
		this.to_account = to_account;
	}

	//public services
	public static List<Transactions> getAllTransactions(long custId) throws InvalidInputException {
		return DataCollection.getAllTransactions(custId);
	}
	
	public static boolean withdraw(long accNum, Transactions t, double updatedAmount) throws InsufficientAmountException {
		return DataCollection.withdraw(accNum, t, updatedAmount);
	}
	
	public static boolean deposit(Connection connection, long accNum, Transactions t, double deductAmt) throws InvalidInputException, SQLException, InsufficientAmountException {
		return DataCollection.deposit(accNum, t, deductAmt);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Transaction number: "+this.transactionNum+", to_account_num : "+this.to_account
				+", from_account_num: "+this.from_account+", amount: "+this.amount+", description: "+this.description;
	}
}
