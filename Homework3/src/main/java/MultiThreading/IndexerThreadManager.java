package MultiThreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import extractor.Extractor;
import extractor.ExtractorManager;
import index.IndexManager;
import index.Indexer;

public class IndexerThreadManager  implements Runnable{
	
	final static Logger logger = LogManager.getLogger(IndexerThreadManager.class.getName());
	
	private Thread t;
	private String threadName;
	private Indexer indexer;
	
	
	public IndexerThreadManager(String threadName, Indexer indexer) {
		super();
		this.threadName = threadName;
		this.indexer = indexer;
		
		logger.trace("Creating " +  threadName );
	}
	
	
	public void run() {

	      logger.trace("Running " +  threadName );
	      try {
	    	  
//	    	  while (ExtractorManager.UrlsToExtract.peek()!= null )
	    	  while(IndexManager.status)	    		  
	  		  {
	    		  
	    	  Indexer.PollContinuously();
	    	  Thread.sleep(100);
//	    	  
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
