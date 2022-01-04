package bus;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import data.*;
public class Manager {

	public static void createCustomer() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.print("\nFirst Name: ");
			String firstName = input.next();
			
			System.out.print("\nLast Name: ");
			String lastName = input.next();
			
			Person person = new Person(firstName,lastName);
			
			System.out.print("\nGenerate your pin: ");
			int pin = input.nextInt();
			
			System.out.print("\nDeposit initial balance: ");
			double initialBalance = input.nextDouble();
			
			System.out.print("\nExtra fee: ");
			double extraFee = input.nextInt();
			
			System.out.print("\nLimit: ");
			int limit = input.nextInt();
			
			CheckingAccount checkingAccount = new CheckingAccount(initialBalance, extraFee, limit);
			
			Customer cust = new Customer(person, pin, checkingAccount);
			
			cust.addNewAccount(checkingAccount);
			Bank.addCustomer(cust);
			DataCollection.writeToSerializedFile(Bank.getListOfCustomers());
			System.out.println("New Customer added!");
		} catch(Exception e) {
		
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void assignCustomerToAccount() throws InvalidInputException, ClassNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter Identification Number of Customer: ");
		
		try {
			
			long custId = input.nextLong();
			Validator.isPositive(custId);
			Customer cust = Bank.search(custId);
			
			if(cust != null) {
				int choice = 0;
				
				do {
					System.out.println("\nChoose an Account to open\n``````````````````````````");
					System.out.println("\n1. Savings Account");
					System.out.println("\n2. Checking Account");
					System.out.println("\n3. Currency Account");
					System.out.println("\n4. Credit Customer");
					System.out.println("\n9. Exit");
					
					choice = input.nextInt();
					
					if(choice == 1) {
						System.out.print("\nOpening Saving Account\n````````````````````````");
						
						System.out.print("\nDeposit initial balance: ");
						double initialBalance = input.nextDouble();
						
						System.out.print("\nExtra fee: ");
						double extraFee = input.nextDouble();
						
						System.out.print("\nInterest Rate ");
						double interestRate = input.nextDouble();
						
						System.out.print("\nAnnual Gain: ");
						double annualGain = input.nextDouble();
						
						SavingAccount savingAccount = new SavingAccount(initialBalance, extraFee, interestRate,annualGain);
						
						cust.addNewAccount(savingAccount);
						System.out.println("\nSaving Account opened and assigned to customer!");
						
						break;
					} else if(choice == 2) {
						
						System.out.print("\nOpening Checking Account\n````````````````````````");
						
						System.out.print("\nDeposit initial balance: ");
						double initialBalance = input.nextDouble();
						
						System.out.print("\nExtra fee: ");
						double extraFee = input.nextDouble();
						
						System.out.print("\nLimit: ");
						int limit = input.nextInt();
						
						CheckingAccount checkingAccount = new CheckingAccount(initialBalance, extraFee, limit);
						
						cust.addNewAccount(checkingAccount);
						System.out.println("\nChecking Account opened and assigned to customer!");
						
						break;
					}else if(choice == 3) {
						
						System.out.print("\nOpening Currency Account\n````````````````````````");
						
						System.out.print("\nEnter Currency [USD, INR, GBP] ?: ");
						
						String currency = input.next();
						EnumCurrency currency2 = EnumCurrency.UNDEFINED;
						
						System.out.print("\nDeposit initial balance: ");
						double initialBalance = input.nextDouble();
						
						System.out.print("\nExtra fee: ");
						double extraFee = input.nextDouble();
						
						if(currency.equalsIgnoreCase("inr")) {
							currency2 = EnumCurrency.INR;
						} else if(currency.equalsIgnoreCase("usd")){
							currency2 = EnumCurrency.USD;
						} else if(currency.equalsIgnoreCase("gbp")) {
							currency2 = EnumCurrency.GBP;
						} 
						
						CurrencyAccount currencyAccount = new CurrencyAccount(initialBalance, extraFee, currency2);
						
						cust.addNewAccount(currencyAccount);
						System.out.println("\nCurrency Account opened and assigned to customer!");
						break;
					}else if(choice == 4) {
						System.out.print("\nOpening Credit Account\n````````````````````````");
						
						System.out.print("\nDeposit initial balance: ");
						double initialBalance = input.nextDouble();
						
						System.out.print("\nExtra fee: ");
						double extraFee = input.nextDouble();
						
						System.out.print("\nLimit Amount: ");
						double limitAmt = input.nextDouble();
						
						CreditAccount creditAccount = new CreditAccount(initialBalance, extraFee, limitAmt);
						
						cust.addNewAccount(creditAccount);
						System.out.println("\nCredit Account opened and assigned to customer!");
						
						break;
					} else {
						System.out.println("\n>> Invalid Input! <<");
					}
					
				} while(choice != 9);
				
				DataCollection.writeToSerializedFile(Bank.getListOfCustomers());
			} else {
				System.out.print("\n >> No customer found! <<");
			}
		} catch(InputMismatchException | InvalidInputException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public static void removeCustomer() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter Identification Number of Customer: ");
		try {
			long custId = input.nextLong();
			
			Customer cust = Bank.search(custId);
			
			if(cust != null) {
				try {
					Bank.removeCustomer(cust);
					DataCollection.writeToSerializedFile(Bank.getListOfCustomers());
					System.out.println("Customer removed!");
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			} else {
				System.out.print("\n >> No customer found! <<");
			}
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void closeAccount() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter Identification Number of Customer: ");
		try {
			long custId = input.nextLong();
			
			Customer cust = Bank.search(custId);
			
			if(cust != null) {
				System.out.print("\nEnter Account Number: ");
				
				long accountNum = input.nextLong();
				
				boolean didDelete = cust.removeAccount(accountNum);
				
				if(didDelete) {
					System.out.print("\n >> Account deleted! <<");
					DataCollection.writeToSerializedFile(Bank.getListOfCustomers());
				} else {
					System.out.print("\n >> Invalid Account number! <<");
				}
				
			} else {
				System.out.print("\n >> No customer found! <<");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void viewAllCustomers() {
		try {
			for(int i = 0;i<Bank.getListOfCustomers().size();i++) {
				
				System.out.println(Bank.getListOfCustomers().get(i).toString()); 
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
