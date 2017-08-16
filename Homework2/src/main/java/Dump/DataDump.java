package Dump;

import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import crawler.Crawler;
import crawler.CrawlerManager;

public class DataDump {
	
	final static Logger logger = LogManager.getLogger(Crawler.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		try {
			  
			MongoClient  mg= new MongoClient("localhost",27017);
			DB db = mg.getDB("mydb");
		    logger.trace("Connect to database successfully");
		    DBCollection  collection = db.getCollection(CrawlerManager.Coll+"_ext");
		    logger.trace("Connect to Collection "+CrawlerManager.Coll+"_ext");
		    
		    
		    try {
	        	
	           	DBCursor cursor = collection.find();
	 
	        		
	            while (cursor.hasNext()) { 

	               DBObject resultElement = cursor.next();
	               
	               System.out.println(resultElement);
	               
	               
	            }

	        }
	        catch (MongoException e) {
	        	
	        	logger.error(e);
	        	e.printStackTrace();
	            
	            
	        }
		    
		    
		} catch (UnknownHostException e) {
			
			logger.error(e);
			e.printStackTrace();
			
		} 

	}

}
