package bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import data.DataCollection;

public abstract class Bank {
	
	private static List<Customer> listOfCustomers =  new ArrayList<Customer>();

	public static List<Customer> getListOfCustomers() throws ClassNotFoundException, IOException {
		listOfCustomers = DataCollection.readFromSerializedFile();
		return listOfCustomers;
	}

	public static void setListOfCustomers(List<Customer> listOfCustomers) {
		Bank.listOfCustomers = listOfCustomers;
	}
	
	public static Customer search(long idNum) {
		
		for(int i = 0;i<listOfCustomers.size();i++) {
		
			if(listOfCustomers.get(i).getIdentificationNum() == idNum) {
				return listOfCustomers.get(i);
			}
		}

		return null;
		
	}
	
	public static Account searchAccountNum(long accNum) {
		
		for(int i = 0;i<listOfCustomers.size();i++) {
			Customer customer = listOfCustomers.get(i);
			
			List<Account> accounts = customer.getListOfAccounts();
			
			for(int j = 0;j<accounts.size();j++) {
				
				
				if(accounts.get(j).getAccountNum() == accNum) {
					return accounts.get(j);
				}
			}
			
		}
		
		return null;
		
	}
	public static void deposit(long accNum, double amt,Transactions t) throws InvalidInputException, IOException, ClassNotFoundException {
		
		Account account = searchAccountNum(accNum);
		
		double finalAmt = account.getAmount() + amt;
		account.setAmount(finalAmt);
		
		account.addTransaction(t);
		
		DataCollection.writeToSerializedFile(getListOfCustomers());
	}
	
	
	public static void withdraw(long accNum, double amt,Transactions t) throws InvalidInputException, IOException, ClassNotFoundException {
		
		Account account = searchAccountNum(accNum);
		double finalAmt = account.getAmount() - amt;
		account.setAmount(finalAmt);
		account.addTransaction(t);
		
		DataCollection.writeToSerializedFile(getListOfCustomers());
	}
	
	public static void addCustomer(Customer cust) throws IOException, ClassNotFoundException {
		listOfCustomers.add(cust);
		
		DataCollection.writeToSerializedFile(listOfCustomers);
		listOfCustomers = DataCollection.readFromSerializedFile();
		
	}

	public static void removeCustomer(Customer cust) throws IOException, ClassNotFoundException {
		listOfCustomers.remove(cust);
		
		DataCollection.writeToSerializedFile(listOfCustomers);
		listOfCustomers = DataCollection.readFromSerializedFile();
	}
	
	public static long uniqueAccountNumber() {
		 Random rand = new Random(); 
		 int upperbound = 100001;
		 long randNum = 0;
		 boolean match = false;
		 
		 do {
			 randNum = Long.valueOf(rand.nextInt(upperbound)+1);
			 
			 for(int i = 0;i<listOfCustomers.size();i++) {
				 
					List<Account> accounts = listOfCustomers.get(i).getListOfAccounts();
					
					for(int j = 0;j<accounts.size();j++) {
						
						if(accounts.get(j).getAccountNum() == randNum) 
							match = true;
	
					}
					
				} 
			 
			 if(!match)
				 break;
			 
		 } while(true);
		 
		return randNum;
	}
	
	
	public static long uniqueCustomerIdNum() {
		
		 Random rand = new Random(); 
		 int upperbound = 100001;
		 long randNum = 0;
		 boolean match = false;
		 
		 do {
			 randNum = Long.valueOf(rand.nextInt(upperbound)+1);
			 
			 for(int i = 0;i<listOfCustomers.size();i++) {
				
				if(listOfCustomers.get(i).getIdentificationNum() == randNum)
					match = true;
						
			} 	 
			 
			if(!match) 
				break;
			 
		 } while(true);
		 
		return randNum;
	}
}
