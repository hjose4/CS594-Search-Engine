package MultiThreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import extractor.ExtractorManager;

public class ExtractorThread implements Runnable{
	
	final static Logger logger = LogManager.getLogger(ExtractorThread.class.getName());
	
	private Thread t;
	private String threadName;
	private ExtractorManager extractor;
	
	
	public ExtractorThread(String threadName, ExtractorManager extractor) {
		super();
		this.threadName = threadName;
		this.extractor = extractor;
		
		logger.trace("Creating " +  threadName );
		
	}
	
	
	public void run() {

	      logger.trace("Running " +  threadName );
	      
	      extractor.execute();
//	      extractor.execute();
////	    	  while (ExtractorManager.UrlsToExtract.peek()!= null )
//	    	  while(ExtractorManager.status || CrawlerManager.status)
//	    		  
//	  		  {
//	    		  
//	    	  extractor.PollContinuously();
//	    	  Thread.sleep(100);
//	    	  
//	  		  }
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
