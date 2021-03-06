package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bus.*;


public abstract class DataCollection {
	
	static Connection connection = ConnectionDB.getInstance();
	
	public static boolean addCustomer(Customer c) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "insert into CUSTOMER(CUSTOMER_ID,FIRST_NAME,LAST_NAME,SIN) values(?,?,?,?)";
		String sqlQuery2 = "insert into ACCOUNT(ACCOUNT_NUM,ACCOUNT_CUST_ID,ACC_TYPE,AMOUNT,EXTRA_FEE) values(?,?,?,?,?)";
		String sqlQuery3 = "insert into CHECKING_ACCOUNT(ACCOUNT_NUM,TRAC_LIMIT) values(?,?)";
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1, c.getIdentificationNum());
			preparedStatement.setString(2, c.getName().getFirstName());
			preparedStatement.setString(3, c.getName().getLastName());
			preparedStatement.setLong(4, c.getPinNum());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sqlQuery2);
			
			preparedStatement.setLong(1, c.getCheckingAccount().getAccountNum());
			preparedStatement.setLong(2, c.getIdentificationNum());
			preparedStatement.setString(3, "PRIMARY");
			preparedStatement.setDouble(4, c.getCheckingAccount().getAmount());
			preparedStatement.setDouble(5, c.getCheckingAccount().getExtraFee());
			
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sqlQuery3);
			
			preparedStatement.setLong(1, c.getCheckingAccount().getAccountNum());
			preparedStatement.setInt(2, c.getCheckingAccount().getLimit());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			
			System.out.println("Customer added!");
			
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	
	public static boolean addAccount(long custId, Account a) {
	  
	  PreparedStatement preparedStatement = null;
	  
	  String sqlQuery = "insert into ACCOUNT(ACCOUNT_NUM,ACCOUNT_CUST_ID,ACC_TYPE,AMOUNT,EXTRA_FEE) values(?,?,?,?,?)"; 
	  
	  String sqlQuery2 = "";
	  
	  String acc_type = "";
	  
	  if(a instanceof SavingAccount) {
		  sqlQuery2 = "insert into SAVING_ACCOUNT(ACCOUNT_NUM,INTEREST_RATE,ANNUAL_GAIN) values(?,?,?)";
		  acc_type = "SAVING";
	  } else if(a instanceof CreditAccount) {
		  sqlQuery2 = "insert into CREDIT_ACCOUNT(ACCOUNT_NUM,AMOUNT_AVAILABLE) values(?,?)";
		  acc_type = "CREDIT";
	  } else if(a instanceof CurrencyAccount) {
		  sqlQuery2 = "insert into CURRENCY_ACCOUNT(ACCOUNT_NUM,CURRENCY_TYPE) values(?,?)";
		  acc_type = "CURRENCY";
	  } else if(a instanceof CheckingAccount) {
		  sqlQuery2 = "insert into CHECKING_ACCOUNT(ACCOUNT_NUM,TRAC_LIMIT) values(?,?)";
		  acc_type = "CHECKING";
	  }
	  
	  try {
	  
		  preparedStatement = connection.prepareStatement(sqlQuery);
		  
		  preparedStatement.setLong(1, a.getAccountNum());
		  preparedStatement.setLong(2, custId);
		  
		  preparedStatement.setString(3, acc_type); 
		  preparedStatement.setDouble(4, a.getAmount()); 
		  preparedStatement.setDouble(5, a.getExtraFee());
		  
		  preparedStatement.executeUpdate();
		  
		  
		  preparedStatement = connection.prepareStatement(sqlQuery2);
		  
		  if(acc_type.equalsIgnoreCase("CHECKING")) {
			  CheckingAccount ch = (CheckingAccount) a;
			  preparedStatement.setLong(1, a.getAccountNum());
			  preparedStatement.setInt(2, ch.getLimit());
			  
		  } else if(acc_type.equalsIgnoreCase("SAVING")) {
			  
			  SavingAccount sa = (SavingAccount) a;
			  preparedStatement.setLong(1, a.getAccountNum());
			  preparedStatement.setDouble(2, sa.getInterestRate());
			  preparedStatement.setDouble(3, sa.getAnnualGain());
			  
		  } else if(acc_type.equalsIgnoreCase("CREDIT")) {
			  
			  CreditAccount ca = (CreditAccount) a;
			  preparedStatement.setLong(1, a.getAccountNum());
			  preparedStatement.setDouble(2, a.getAmount());
			  
		  } else if(acc_type.equalsIgnoreCase("CURRENCY")) {
			  
			  CurrencyAccount cya = (CurrencyAccount) a;
			  preparedStatement.setLong(1, a.getAccountNum());
			  preparedStatement.setString(2, cya.getCurrentType().name());
			  
		  }  
		  
		  preparedStatement.executeUpdate();
		  
		  connection.commit();
		  
		  System.out.println(acc_type+" Account added!");
	  
		  return true; 
		  
	  } catch (SQLException e) { // TODO: handle exception
		  System.err.println(e.getMessage());
		  return false; 
	  }
	  
	  }
	 
	public static List<Customer> getAllCustomers() throws InvalidInputException, SQLException, RaiseException {
		
		List<Customer> listOfCustomers = new ArrayList<Customer>();
		ResultSet resultSet = null;
	
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "Select * from CUSTOMER";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				long identificationNum = resultSet.getLong(1);
				String fname = resultSet.getString(2);
				String lname = resultSet.getString(3);
				long pinNum = resultSet.getLong(4);
				
				Person name = new Person(fname,lname);
				
				Customer c = new Customer();
				c.setIdentificationNum(identificationNum);
				c.setName(name);
				c.setPinNum(pinNum);
				
				 
				listOfCustomers.add(c);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());			
		}
		
		return listOfCustomers;
	}

	public static List<Account> getAllCustomerAccounts(long custId) throws InvalidInputException {
		
		List<Account> listOfAccounts = new ArrayList<Account>();
		ResultSet resultSet = null;
	
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "Select * from ACCOUNT where ACCOUNT_CUST_ID=?";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1, custId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				 Account acc = null;
				 long accountNum = resultSet.getLong(1);
				 double amount = resultSet.getDouble(4);
				 Date openedDate = resultSet.getDate(6);
				 double extraFee = resultSet.getDouble(5);
				 String type = resultSet.getString(3);
				 
				 if(type.equalsIgnoreCase("SAVING")) {
					 acc = new SavingAccount();
				 } else if(type.equalsIgnoreCase("CHECKING") || type.equalsIgnoreCase("PRIMARY")) {
					 acc = new CheckingAccount();
				 } else if(type.equalsIgnoreCase("CREDIT")) {
					 acc = new CreditAccount();
				 } else if(type.equalsIgnoreCase("CURRENCY")) {
					 acc = new CurrencyAccount();
				 }
				 
				 acc.setAccountNum(accountNum);
				 acc.setAmount(amount);
				 acc.setExtraFee(extraFee);
				 acc.setOpenedDate(openedDate);
				 acc.setType(type);
				 
				 listOfAccounts.add(acc);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());			
		}
		
		return listOfAccounts;
	}
	
	public static Customer getCustomer(long custId) throws InvalidInputException, RaiseException {
		
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "SELECT * FROM CUSTOMER INNER JOIN ACCOUNT ON ACCOUNT.ACCOUNT_CUST_ID = CUSTOMER.CUSTOMER_ID where ACCOUNT.ACCOUNT_CUST_ID=? and ACCOUNT.ACC_TYPE='PRIMARY'";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1, custId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				long identificationNum = resultSet.getLong(1);
				String fname = resultSet.getString(2);
				String lname = resultSet.getString(3);
				long pinNum = resultSet.getLong(4);
				
				Person name = new Person(fname,lname);
				 long accountNum = resultSet.getLong(7);
				 Date openedDate = resultSet.getDate(12);
				 double extraFee = resultSet.getDouble(11);
				
				 double amount = resultSet.getDouble(10);
				
				CheckingAccount checkingAccount = new CheckingAccount(amount,extraFee,0);
				checkingAccount.setAccountNum(accountNum);
				checkingAccount.setOpenedDate(openedDate);
				
				Customer c = new Customer();
				c.setIdentificationNum(identificationNum);
				c.setName(name);
				c.setPinNum(pinNum);
				c.setCheckingAccount(checkingAccount);
				 
				return c;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
		}
		return null;
		
	}
	
	public static Account getAccountByNum(long accNum) throws InvalidInputException {
		
		ResultSet resultSet = null;
	
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "Select * from ACCOUNT where ACCOUNT_NUM=?";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1, accNum);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				 Account acc = null;
				 long accountNum = resultSet.getLong(1);
				 double amount = resultSet.getDouble(4);
				 Date openedDate = resultSet.getDate(6);
				 double extraFee = resultSet.getDouble(5);
				 String type = resultSet.getString(3);
				 
				 if(type.equalsIgnoreCase("SAVING")) {
					 acc = new SavingAccount();
				 } else if(type.equalsIgnoreCase("CHECKING") || type.equalsIgnoreCase("PRIMARY")) {
					 acc = new CheckingAccount();
				 } else if(type.equalsIgnoreCase("CREDIT")) {
					 acc = new CreditAccount();
				 } else if(type.equalsIgnoreCase("CURRENCY")) {
					 acc = new CurrencyAccount();
				 }
				 
				 acc.setAccountNum(accountNum);
				 acc.setAmount(amount);
				 acc.setExtraFee(extraFee);
				 acc.setOpenedDate(openedDate);
				 acc.setType(type);
				 
				 return acc;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());			
		}
		
		return null;
	}

	public static List<Transactions> getAllTransactions(long custId) throws InvalidInputException {
		
		List<Transactions> listTransactions = new ArrayList<Transactions>();
		ResultSet resultSet = null;
	
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "SELECT * FROM TRANSACTIONS \r\n"
				+ "INNER JOIN ACCOUNT ON ACCOUNT.ACCOUNT_NUM = TRANSACTIONS.FROM_ACC_NUM or ACCOUNT.ACCOUNT_NUM = TRANSACTIONS.TO_ACC_NUM \r\n"
				+ "INNER JOIN CUSTOMER ON CUSTOMER.CUSTOMER_ID = ACCOUNT.ACCOUNT_CUST_ID WHERE CUSTOMER_ID=?";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1, custId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				 Transactions txns = new Transactions();
				 long transactionNum = resultSet.getLong(1);
				 long from_account = resultSet.getLong(2);
				 long to_account = resultSet.getLong(3);
				 String type = resultSet.getString(4);
				 double amount = resultSet.getDouble(5);
				 String description = resultSet.getString(6);
				 Date transactionDate = resultSet.getDate(7);
				 
				 EnumTransactionType transactionType = EnumTransactionType.UNDEFINED;
				 
				 if(type.equalsIgnoreCase("DEPOSIT")) {
					 transactionType = EnumTransactionType.DEPOSIT;
				 } else if(type.equalsIgnoreCase("WITHDRAW")) {
					 transactionType = EnumTransactionType.WITHDRAW;
				 }
				 
				 txns.setTransactionNum(transactionNum);
				 txns.setFrom_account(from_account);
				 txns.setTo_account(to_account);
				 txns.setTransactionType(transactionType);
				 txns.setAmount(amount);
				 txns.setDescription(description);
				 txns.setTransactionDate(transactionDate);
				 
				 listTransactions.add(txns);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());			
		}
		
		return listTransactions;
	}
	
	public static boolean withdraw(long accNum, Transactions t, double updatedAmount) throws InsufficientAmountException {
		
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "update ACCOUNT set AMOUNT=? where ACCOUNT_NUM=?";
		String sqlQuery2 = "insert into TRANSACTIONS(TRANSACTION_ID, FROM_ACC_NUM, TO_ACC_NUM,TRANSFER_TYPE,AMOUNT,DESCRIPTION,TRANSACTION_DATE) values(?,?,?,?,?,?,?)";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setDouble(1, updatedAmount);
			preparedStatement.setLong(2, t.getFrom_account());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sqlQuery2);
			
			preparedStatement.setLong(1, t.getTransactionNum());
			preparedStatement.setLong(2, t.getFrom_account());
			preparedStatement.setLong(3, t.getTo_account());
			preparedStatement.setString(4, t.getTransactionType().name());
			preparedStatement.setDouble(5, t.getAmount());
			preparedStatement.setString(6, t.getDescription());
			java.sql.Date sqlDate = new java.sql.Date(t.getTransactionDate().getTime()); 
			preparedStatement.setDate(7, sqlDate);
			
			System.out.println(t.getTransactionNum()+" "+t.getFrom_account()+" "+t.getTo_account()+" "+t.getTransactionType().name()+""
			+t.getAmount()+" "+t.getDescription());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
			System.out.println("Withdraw transaction updated!");
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public static boolean deposit(long accNum, Transactions t, double deductAmt) throws SQLException, InvalidInputException,InsufficientAmountException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sqlQuery = "update ACCOUNT set AMOUNT=? where ACCOUNT_NUM=?"; //deduct
		String sqlQuery2 = "insert into TRANSACTIONS(TRANSACTION_ID, FROM_ACC_NUM, TO_ACC_NUM,TRANSFER_TYPE,AMOUNT,DESCRIPTION,TRANSACTION_DATE) values(?,?,?,?,?,?,?)";
		String sqlQuery3 = "select * from ACCOUNT WHERE ACCOUNT_NUM=?";
		String sqlQuery4 = "update ACCOUNT set AMOUNT=? where ACCOUNT_NUM=?"; //deposit
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setDouble(1, deductAmt);
			preparedStatement.setLong(2, t.getFrom_account());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(sqlQuery2);
			
			preparedStatement.setLong(1, t.getTransactionNum());
			preparedStatement.setLong(2, t.getFrom_account());
			preparedStatement.setLong(3, t.getTo_account());
			preparedStatement.setString(4, t.getTransactionType().name());
			preparedStatement.setDouble(5, t.getAmount());
			preparedStatement.setString(6, t.getDescription());
			java.sql.Date sqlDate = new java.sql.Date(t.getTransactionDate().getTime()); 
			preparedStatement.setDate(7, sqlDate);
			
			System.out.println(t.getTransactionNum()+" "+t.getFrom_account()+" "+t.getTo_account()+" "+t.getTransactionType().name()+""
			+t.getAmount()+" "+t.getDescription());
			
			preparedStatement.executeUpdate();
			
			
			preparedStatement = connection.prepareStatement(sqlQuery3);
			
			preparedStatement.setLong(1, t.getTo_account());
			
			resultSet = preparedStatement.executeQuery();
			Account acc = null;
			
			while(resultSet.next()) {
				 
				 long accountNum = resultSet.getLong(1);
				 double amount = resultSet.getDouble(4);
				 Date openedDate = resultSet.getDate(6);
				 double extraFee = resultSet.getDouble(5);
				 String type = resultSet.getString(3);
				 
				 if(type.equalsIgnoreCase("SAVING")) {
					 acc = new SavingAccount();
				 } else if(type.equalsIgnoreCase("CHECKING") || type.equalsIgnoreCase("PRIMARY")) {
					 acc = new CheckingAccount();
				 } else if(type.equalsIgnoreCase("CREDIT")) {
					 acc = new CreditAccount();
				 } else if(type.equalsIgnoreCase("CURRENCY")) {
					 acc = new CurrencyAccount();
				 }
				 
				 acc.setAccountNum(accountNum);
				 acc.setAmount(amount);
				 acc.setExtraFee(extraFee);
				 acc.setOpenedDate(openedDate);
				 acc.setType(type);
			
			}
			
			
			preparedStatement = connection.prepareStatement(sqlQuery4);
			
			double updateAmt = acc.getAmount() + t.getAmount();
			
			preparedStatement.setDouble(1, updateAmt);
			preparedStatement.setLong(2, t.getTo_account());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			
			System.out.println("Withdraw transaction updated!");
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean deleteAccount(long accNum) {
		  
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "delete from ACCOUNT where ACCOUNT_NUM=?";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1,accNum);
		
			
			int deleted = preparedStatement.executeUpdate();
			if (deleted == 0) {
				return false;
			} else {
				connection.commit();
				System.out.println("Account deleted!");
				return true;
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
		}
		return false;
		  
	  }
	
	public static boolean deleteCustomer(long custId) {
		  
		PreparedStatement preparedStatement = null;
		
		String sqlQuery = "delete from CUSTOMER where CUSTOMER_ID=?";
		
		try {
		
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			preparedStatement.setLong(1,custId);
			
			
			int deleted = preparedStatement.executeUpdate();
			if (deleted == 0) {
				return false;
			} else {
				connection.commit();
				
				System.out.println("Customer deleted!");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
		}
		return false;
		  
	  }
	
}
