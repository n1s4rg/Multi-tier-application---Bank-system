package data;

import java.util.HashMap;

import bus.Account;
import bus.CheckingAccount;
import bus.NumberPredicate;
import bus.SavingAccount;

public class SingletonBank {
	private static SingletonBank singleton = new SingletonBank( );
	
	private SingletonBank() {}
	
	public static SingletonBank  getSingleInstance() {
		 return singleton;
	}
	
	public HashMap<Integer, Account> getAccountHashMap() {
		HashMap<Integer, Account> accountHashMap = new HashMap<Integer, Account>();
		return accountHashMap;
	}
	
	public HashMap<Integer, CheckingAccount> getCheckingAccountHashMap() {
		HashMap<Integer, CheckingAccount> CheckingAccountHashMap = new HashMap<Integer, CheckingAccount>();
		return CheckingAccountHashMap;
	}
	
	public HashMap<Integer, SavingAccount> getSavingAccountHashMap() {
		HashMap<Integer, SavingAccount> SavingAccountHashMap = new HashMap<Integer, SavingAccount>();
		return SavingAccountHashMap;
	}
	
	public void add (Account object) {
		HashMap<Integer, Account> accountHashMap = new HashMap<Integer, Account>();
		accountHashMap.put((int) object.getAccountNum(), object);
	}
	
	public void remove (Account object) {
		HashMap<Integer, Account> accountHashMap = new HashMap<Integer, Account>();
		accountHashMap.remove(object);
	}
	
	public void sort (NumberPredicate num) {
		
	}
	
	public void sortByAccountlNumber() {
		
	}
	
}
