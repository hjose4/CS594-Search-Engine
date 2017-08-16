package MultiThreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crawler.CrawlerManager;
import extractor.Extractor;
import extractor.ExtractorManager;

public class ExtractorThreadManager implements Runnable{
	
	final static Logger logger = LogManager.getLogger(CrawlerThreadManager.class.getName());
	
	private Thread t;
	private String threadName;
	private Extractor extractor;
	
	
	public ExtractorThreadManager(String threadName, Extractor extractor) {
		super();
		this.threadName = threadName;
		this.extractor = extractor;
		
		logger.trace("Creating " +  threadName );
	}
	
	
	public void run() {

	      logger.trace("Running " +  threadName );
	      try {
	    	  
//	    	  while (ExtractorManager.UrlsToExtract.peek()!= null )
	    	  while(ExtractorManager.status || CrawlerManager.status)
	    		  
	  		  {
	    		  
	    	  extractor.PollContinuously();
	    	  Thread.sleep(100);
	    	  
	  		  }

//	          Thread.sleep(100);
	         
	     } catch (InterruptedException e) {
	         logger.error("Thread " +  threadName + " interrupted.");
	     }
	     logger.trace("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start ()
	   {
	      logger.trace("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	

}
