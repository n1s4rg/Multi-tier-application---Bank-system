package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import bus.*;


public class DataCollection {
	
	public static void writeToSerializedFile(List<Customer> listOfCustomers) throws IOException
	{		
		FileOutputStream  fileStream = new FileOutputStream("src//bank.ser");
		
		ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);		
		
		outputStream.writeObject(listOfCustomers);
		fileStream.close();		
	}	
	
	@SuppressWarnings("unchecked")
	public static List<Customer>  readFromSerializedFile() throws IOException, ClassNotFoundException
	{
		List<Customer> listOfCustomers = new ArrayList<Customer>();
	    
		FileInputStream fileStream = new FileInputStream("src//bank.ser");
	    
	    ObjectInputStream inputStream = new ObjectInputStream(fileStream);		  
	    listOfCustomers  = (List<Customer>) inputStream.readObject();		  
	    
	    inputStream.close();			  
	    return listOfCustomers;	
	}
}
