package database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crawler.Crawler;

public class DbClient extends DbConnect{
	
	final static Logger logger = LogManager.getLogger(Crawler.class.getName());
	
	public String collection;

	public DbClient(String collection) {

		super("localhost", "27017", "mydb", collection);
		
		

	}

}
