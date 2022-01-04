package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	private Connection connection;
	private static ConnectionDB connectionDB;

    private ConnectionDB() {
    	String userName, password, service, url;
    	
        userName = "fortisbank";
    	password = "123";
    	service = "localhost";
    	
    	url = "jdbc:oracle:thin:"+userName+"/"+password+"@"+service;
    	
    	try {
    		connection = DriverManager.getConnection(url);
    		System.out.println("DB connected successfully!");
    	}catch(Exception exception) {
    		exception.printStackTrace();
    	}
    }

    public static Connection getInstance() {
        if (connectionDB == null) {
        	connectionDB = new ConnectionDB();
        }
        return connectionDB.connection;
    }
   
}
