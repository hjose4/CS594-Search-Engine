package MultiThreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import core.index;
import Util.Extract;
import Util.writeJSON;
import Util.StopWord;
import extractor.ExtractorManager;
import model.Page;

public class ExtractThread implements Runnable{
	
	final static Logger logger = LogManager.getLogger(ExtractThread.class.getName());
	
	private Thread t;
	private String threadName;
	private Extract extract;
	
	
	public ExtractThread(String threadName, Extract extract) {
		super();
		this.threadName = threadName;
		this.extract = extract;
		
//		logger.trace("Creating " +  threadName );
		
	}
	
	
	public void run() {

//	     logger.trace("Running " +  threadName );
		
		Page p = new Page();
		
		p = extract.getDetails();
	      
		index.extracted(p);
		
		StopWord.execute(p);
		
//		writeJSON.execute(p);
		

//	     logger.trace("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start ()
	   {
//	      logger.trace("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	

}
