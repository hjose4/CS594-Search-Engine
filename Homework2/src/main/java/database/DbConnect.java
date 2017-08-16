package database;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import crawler.Crawler;
import model.Raw;


public class DbConnect {
	
	private MongoClient mg = null;
	private DB db = null;
	private DBCollection collection = null;
	
	final static Logger logger = LogManager.getLogger(Crawler.class.getName());
	
	private String host;
	private String port;
	private String dbName;
	private String colName;
	
	
	public DbConnect(String host, String port, String dbName, String colName) {
		
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.colName = colName;
		
		logger.trace("Host : "+this.host+"Port : "+this.port+"DB : "+this.dbName+"Collection : "+this.colName);
		
		connect();
	}




	public void connect()
	{
		 try {
			  
			   mg= new MongoClient(host,Integer.parseInt(port));
			   db = mg.getDB(dbName);
		       logger.trace("Connect to database successfully");
		       collection = db.getCollection(colName);
		       logger.trace("Connect to Collection "+colName);
		} catch (UnknownHostException e) {
			
			logger.error(e);
			e.printStackTrace();
			
		} 
	}
	
	
	public boolean Insert(String link, String doc, String date) {

        try {
        	BasicDBObject document = new BasicDBObject();
           	document.put("Url", link);
           	document.put("Source", doc);
           	document.put("Date Time", date);

//           	BasicDBObject documentDetail = new BasicDBObject();
//           	documentDetail.put("records", 99);
//           	documentDetail.put("index", "vps_index1");
//           	documentDetail.put("active", "true");
//           	document.put("detail", documentDetail);

           	collection.insert(document);

        }
        catch (MongoException e) {
        	
        	logger.error(e);
        	e.printStackTrace();
            return false;
            
        }

        return true;
    }
	
	
	public boolean Insert(String url, String title, String path, String crawldate, String bodytext, String description, String keywords, ArrayList<String> metadata, ArrayList<String> Img, ArrayList<String> script, ArrayList<String> imports, ArrayList<String> links, String extractdate) {

		
		
        try {
        	BasicDBObject document = new BasicDBObject();
           	document.put("Url", url);
           	document.put("Title", title);
           	document.put("FilePath", path);
           	document.put("CrawlDateTime", crawldate);
           	document.put("BodyText", bodytext);
           	document.put("Description", description);
           	document.put("Keywords", keywords);
           	document.put("Metadata", metadata);
           	document.put("Images", Img);
           	document.put("Scripts", script);
           	document.put("Import", imports);
           	document.put("HyperLinks", links);
           	document.put("ExtractDateTime", extractdate);

//           	BasicDBObject documentDetail = new BasicDBObject();
//           	documentDetail.put("records", 99);
//           	documentDetail.put("index", "vps_index1");
//           	documentDetail.put("active", "true");
//           	document.put("detail", documentDetail);

           	collection.insert(document);

        }
        catch (MongoException e) {
        	
        	logger.error(e);
        	e.printStackTrace();
            return false;
            
        }

        return true;
    }
	
	
	public ArrayList<Raw> Retrive(int s) {
		
		ArrayList<Raw> list = new ArrayList<Raw>();

        try {
        	
           	DBCursor cursor = collection.find().skip(s);
 
        		
            while (cursor.hasNext()) { 

               DBObject resultElement = cursor.next();

               Map resultElementMap = resultElement.toMap();

               Collection resultValues = resultElementMap.values();
               
               Raw r = new Raw(resultValues.toArray()[1].toString(), resultValues.toArray()[2].toString(), resultValues.toArray()[3].toString());
               
               list.add(r);

            }

        }
        catch (MongoException e) {
        	
        	logger.error(e);
        	e.printStackTrace();
            return null;
            
        }

        return list;
    }

	
	
	
	
	
	
}
