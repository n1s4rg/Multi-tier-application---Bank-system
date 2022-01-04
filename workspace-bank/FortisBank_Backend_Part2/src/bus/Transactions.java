package bus;

import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;

public class Transactions implements Serializable  {
	
	private long transactionNum = 0;
	private String description;
	private Date transactionDate;
	private EnumTransactionType transactionType;
	private double amount;
	
	public Transactions() {
		super();
		this.transactionNum++;
		this.description = "";
		this.transactionDate = Calendar.getInstance().getTime();
		this.transactionType = EnumTransactionType.UNDEFINED;
		this.amount = 0.0;
	}
	
	public Transactions(String description,
			EnumTransactionType transactionType, double amount) {
		super();
		this.transactionNum++;
		this.description = description;
		this.transactionDate = Calendar.getInstance().getTime();
		this.transactionType = transactionType;
		this.amount = amount;
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
	
}
