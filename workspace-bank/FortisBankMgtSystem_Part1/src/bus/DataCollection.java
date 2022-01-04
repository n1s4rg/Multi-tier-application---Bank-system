package bus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataCollection {
	public static void writeToSerializedFile(List<Customer> listOfCustomers) throws IOException
	{		
		FileOutputStream  fileStream = new FileOutputStream("src//data//customer.ser");
		
		ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);		
		
		outputStream.writeObject(listOfCustomers);
		fileStream.close();		
	}	
	
	@SuppressWarnings("unchecked")
	public static List<Customer>  readFromSerializedFile() throws IOException, ClassNotFoundException
	{
		List<Customer> listOfCustomers = new ArrayList<Customer>();
	    
		FileInputStream fileStream = new FileInputStream("src//data//customer.ser");
	    
	    ObjectInputStream inputStream = new ObjectInputStream(fileStream);		  
	    listOfCustomers  = (List<Customer>) inputStream.readObject();		  
	    
	    inputStream.close();			  
	    return listOfCustomers;	
	}
}
