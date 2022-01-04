package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


import bus.Customer;

public class FileManager {
	public void serialize (HashMap<String, Customer> listOfCustomers) throws IOException{
		FileOutputStream  fileStream = new FileOutputStream("src//bank.ser");
		
		ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);		
		
		outputStream.writeObject(listOfCustomers);
		fileStream.close();		
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Customer> deSerialize() throws IOException, ClassNotFoundException{
		HashMap<String, Customer> listOfCustomers = new HashMap<String, Customer>();
	    
		FileInputStream fileStream = new FileInputStream("src//bank.ser");
	    
	    ObjectInputStream inputStream = new ObjectInputStream(fileStream);		  
	    listOfCustomers  = (HashMap<String, Customer>) inputStream.readObject();		  
	    
	    inputStream.close();			  
	    return listOfCustomers;
	}
}
