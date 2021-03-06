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

public class Tester1 {

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
			System.out.println("[1 - Bank Manager]");
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
							int option;
							
							do {
								System.out.println("\nHello Bank Manager\n``````````````````````````");
								System.out.println("\n1. Create Customer");
								System.out.println("\n2. Open an Account");
								System.out.println("\n3. Close an Account");
								System.out.println("\n4. Remove Customer");
								System.out.println("\n5. View All Customers");
								System.out.println("\n9. Logoff");
								
								System.out.print("\nEnter choice?: ");
								option = input.nextInt();
								if(option == 9) {
									break;
								}
								
								if(option == 1) {
									
									//Create Customer
									Manager.createCustomer();
									
								} else if(option == 2) {
									
									//Open Account
									Manager.assignCustomerToAccount();
									
								} else if(option == 3) {
									
									//close Account
									Manager.closeAccount();
									
								} else if(option == 4) {
									
									//remove customer
									Manager.removeCustomer();
									
								}else if(option == 5) {
									
									//remove customer
									Manager.viewAllCustomers();
									
								} else {
									System.out.print("\n>> Invalid choice <<");
								}
								
							} while(option != 9);
							
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
 *
	Fortis Bank

	 Login as
````````````````````````````
[1 - Bank Manager]
[9 - Exit]

Enter your option?: 1

Hello Bank Manager
``````````````````````````

1. Create Customer

2. Open an Account

3. Close an Account

4. Remove Customer

5. View All Customers

9. Logoff

Enter choice?: 5
Identification Number :70793, Name: Samiuddin Syed
Identification Number :18329, Name: Deep Vag

Hello Bank Manager
``````````````````````````

1. Create Customer

2. Open an Account

3. Close an Account

4. Remove Customer

5. View All Customers

9. Logoff

Enter choice?: 2

Enter Identification Number of Customer: 18329

Choose an Account to open
``````````````````````````

1. Savings Account

2. Checking Account

3. Currency Account

4. Credit Customer

9. Exit
2

Opening Checking Account
````````````````````````
Deposit initial balance: 457

Extra fee: 5

Limit: 8

Checking Account opened and assigned to customer!

Hello Bank Manager
``````````````````````````

1. Create Customer

2. Open an Account

3. Close an Account

4. Remove Customer

5. View All Customers

9. Logoff

Enter choice?:   */
