package MultiThreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crawler.Crawler;
import crawler.CrawlerManager;



public class CrawlerThreadManager implements Runnable {
	
	final static Logger logger = LogManager.getLogger(CrawlerThreadManager.class.getName());
	
	private Thread t;
	private String threadName;
	private Crawler crawl;

	public CrawlerThreadManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CrawlerThreadManager(String threadName, Crawler crawler) {
		super();
		this.threadName = threadName;
		this.crawl = crawler;
		logger.trace("Creating " +  threadName );
	}
	
	public void run() {

	      logger.trace("Running " +  threadName );
	      try {
	    	  
	    	  while (CrawlerManager.status )
	    		  
	  		  {
	    		  
	    	  crawl.PollContinuously();
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
