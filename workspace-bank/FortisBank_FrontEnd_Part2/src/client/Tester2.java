package client;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bus.Account;
import bus.Bank;
import bus.CheckingAccount;
import bus.CreditAccount;
import bus.CurrencyAccount;
import bus.Customer;
import data.DataCollection;
import bus.EnumTransactionType;
import bus.InvalidInputException;
import bus.Manager;
import bus.SavingAccount;
import bus.Transactions;
import bus.Validator;

public class Tester2 {

	public static void main(String[] args) throws InvalidInputException, ClassNotFoundException, IOException {
		
		int choice = 0;
		Scanner input = new Scanner(System.in);
		
		try {
			Bank.setListOfCustomers(DataCollection.readFromSerializedFile());			
		} catch(Exception e) {
			
		}
		
		do {
			System.out.println("\n\tFortis Bank");
			System.out.println("\n\t Login as\n````````````````````````````");
			System.out.println("[1 - Customer]");
			System.out.println("[9 - Exit]");
			
			
			try {
				
				System.out.print("\nEnter your option?: ");
				
				choice = input.nextInt();
				
				if(choice == 9) {
					System.out.println("Goodbye!");
					break;
				}
				
				if (choice < 1 || choice > 1) {
					System.out.println("\n>>> Invalid Choice <<<");
				} else {
					
					switch (choice) {
						// manager
						
						case 1:
							//customer
							long custId = 0;
							
							//ask custId
							do {
								System.out.println("\n\tCustomer Login\n```````````````````````````````");
								System.out.print("Enter Identification Number ?: ");
								
								custId = input.nextLong();
								
								if(custId == -1) {
									break;
								}
								
								Customer cust = Bank.search(custId);
								
								if(cust != null) {
									int loginCust = 0;
									
									do {
										System.out.println("\nHello "+ cust.getName().getLastName() +"\n``````````````````````````");
										System.out.println("\n1. Deposit");
										System.out.println("\n2. Withdraw");
										System.out.println("\n3. View all my accounts");
										System.out.println("\n4. View Checking Account");
										System.out.println("\n9. Logoff");
										
										System.out.print("\nEnter choice?: ");
										loginCust = input.nextInt();
										
										if(loginCust == 9) {
											break;
										}
										
										if(loginCust == 1) {
											//Deposit
											
											System.out.print("\nEnter Account Number to deposit: ");
											
											long accountNum = input.nextLong();
											
											Account acc = Bank.searchAccountNum(accountNum);
											
											if(acc != null) {
												System.out.print("\nEnter description: ");
												
												String desc = input.next();
												
												System.out.print("\nEnter Amount to deposit: ");
												
												double depositAmt = input.nextDouble();
												
												Transactions transaction = new Transactions(desc,EnumTransactionType.DEPOSIT,depositAmt);
												Bank.deposit(accountNum,depositAmt, transaction);
												
											} else {
												System.out.print("\n >> No Account found! <<");
											}
		
										} else if(loginCust == 2) {
											//Withdraw
											int withOpt = 0;
											System.out.print("\nEnter Account Number to Withdraw from: ");
											
											long accountNum = input.nextLong();
											
											Account acc = Bank.searchAccountNum(accountNum);
											
											if(acc != null) {
												System.out.print("\nEnter description: ");
												
												String desc = input.next();

												System.out.print("\nEnter Amount to withdraw: ");
												
												double withdrawAmt = input.nextDouble();
												
												if (acc.getAmount() > withdrawAmt) {
													Transactions transaction = new Transactions(desc, EnumTransactionType.WITHDRAW,withdrawAmt);
													
													Bank.withdraw(accountNum, withdrawAmt, transaction);
												} else {
													System.out.print("\n >> Insufficient Account balance! <<");
												}
												
												
											} else {
												System.out.print("\n >> No Account found! <<");
											}
											
										} else if(loginCust == 3) {
											//View all
											List<Account> listOfAccounts = cust.getListOfAccounts();
											
											for(Account account: listOfAccounts) {
												
												if(account instanceof CheckingAccount) {
													
													CheckingAccount acc = (CheckingAccount)account;
													System.out.println(acc.viewAccount());
													
												} else if(account instanceof SavingAccount) {
													
													
													SavingAccount acc = (SavingAccount)account;
													System.out.println(acc.viewAccount());
													
												}else if(account instanceof CreditAccount) {
													
													CreditAccount acc = (CreditAccount)account;
													System.out.println(acc.viewAccount());
													
												}else if(account instanceof CurrencyAccount) {
													
													CurrencyAccount acc = (CurrencyAccount)account;
													System.out.println(acc.viewAccount());
													
												}
												
											}
											
										} else if(loginCust == 4) {
											//view checking account
											
											System.out.println(cust.getCheckingAccount().viewAccount());
											
										} else {
											System.out.print("\n>> Invalid choice <<");
										}
										
									} while(loginCust != 9);
									
									
								} else {
									System.out.println("\n>> Invalid Identification number! <<");
									System.out.println("\n>> To go back, enter -1 <<");
								}
							
							} while(custId != -1);
							
							break;
						
					}
				}
			} catch(InputMismatchException | InvalidInputException e) {
				input.next();
				
				System.out.println("Invalid input!");
				
			} 
			
		} while (choice != 9);
	
	}

}

/*
 
Fortis Bank



Login as
````````````````````````````
[1 - Customer]
[9 - Exit]



Enter your option?: 1



Customer Login
```````````````````````````````
Enter Identification Number ?: 70793



Hello Syed
``````````````````````````



1. Deposit



2. Withdraw



3. View all my accounts



4. View Checking Account



9. Logoff



Enter choice?: 3
Checking Account Account Number: 64362, Balance: 433.0, Limit: 2, Extra Fee: 4.0



Hello Syed
``````````````````````````



1. Deposit



2. Withdraw



3. View all my accounts



4. View Checking Account



9. Logoff



Enter choice?: 2



Enter Account Number to Withdraw from: 64362



Enter description: exam



Enter Amount to withdraw: 100



Hello Syed
``````````````````````````



1. Deposit



2. Withdraw



3. View all my accounts



4. View Checking Account



9. Logoff



Enter choice?: 4
Checking Account Account Number: 64362, Balance: 333.0, Limit: 2, Extra Fee: 4.0



Hello Syed
``````````````````````````



1. Deposit



2. Withdraw



3. View all my accounts



4. View Checking Account



9. Logoff



Enter choice?:
 * */
