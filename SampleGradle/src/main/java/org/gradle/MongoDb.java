package org.gradle;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDb {

	public static void main(String[] args) {
		
		String myUserName= "admin";
    	char[] myPassword= {'p','a','s','s','w','o','r','d'};
    	
    	
   try {
	  
	   MongoClient mg= new MongoClient("localhost",27017);
	   DB db = mg.getDB( "mydb" );
       System.out.println("Connect to database successfully");
       boolean auth = db.authenticate(myUserName, myPassword);
       System.out.println("Authentication: "+auth);
} catch (UnknownHostException e) {
	
	e.printStackTrace();
} catch (IOException e) {
	
	e.printStackTrace();
}

	}

}
