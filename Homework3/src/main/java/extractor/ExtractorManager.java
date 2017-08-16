package extractor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import core.index;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MultiThreading.ExtractorThreadManager;



public class ExtractorManager {
	
	final static Logger logger = LogManager.getLogger(ExtractorManager.class.getName());
//	
//	public static Queue<Raw> UrlsToExtract = new LinkedList<Raw>(); 
//	
//	public static int start = 0;
//	
//	public static int end = 0;
//	 
	
	public static boolean status = true;

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		
//		execute();
//        
//
//	}
	
	public static void execute()
	{		
		
		Extractor e1 = new Extractor(index.UrlsToExtract,index.UrlsExtracted);
		ExtractorThreadManager t1 = new ExtractorThreadManager("Ext Thread 1",e1);
		t1.start();
		
		Extractor e2 = new Extractor();
		ExtractorThreadManager t2 = new ExtractorThreadManager("Ext Thread 2",e2);
		t2.start();

		Extractor e3 = new Extractor();
		ExtractorThreadManager t3 = new ExtractorThreadManager("Ext Thread 3",e3);
		t3.start();

		Extractor e4 = new Extractor();
		ExtractorThreadManager t4 = new ExtractorThreadManager("Ext Thread 4",e4);
		t4.start();

		Extractor e5 = new Extractor();
		ExtractorThreadManager t5 = new ExtractorThreadManager("Ext Thread 5",e5);
		t5.start();
		
		Extractor e6 = new Extractor();
		ExtractorThreadManager t6 = new ExtractorThreadManager("Ext Thread 6",e6);
		t6.start();
		
		Extractor e7 = new Extractor();
		ExtractorThreadManager t7 = new ExtractorThreadManager("Ext Thread 7",e7);
		t7.start();
		
		Extractor e8 = new Extractor();
		ExtractorThreadManager t8 = new ExtractorThreadManager("Ext Thread 8",e8);
		t8.start();
		
		Extractor e9 = new Extractor();
		ExtractorThreadManager t9 = new ExtractorThreadManager("Ext Thread 9",e9);
		t9.start();
		
		Extractor e10 = new Extractor();
		ExtractorThreadManager t10 = new ExtractorThreadManager("Ext Thread 10",e10);
		t10.start();
//		
//				
//		try {
//		
//		
//		while (CrawlerManager.status)
//		{
//		Thread.sleep(4000);
//		
//		logger.trace("waiting for crawler to complete...");			
//		
//		RetriveData();
//		
//		Thread.sleep(6000);
//		
//		}
//				
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		RetriveData();
//		
		while(index.UrlsToExtract.peek()!= null)
		{
			try {
				Thread.sleep(1000);
				
				logger.trace("to go :"+index.UrlsToExtract.size());
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (index.UrlsToExtract.size()==0)
		{
			status = false;
		}
//		
//		if (!CrawlerManager.status && !status)
//		{
//			
//		logger.trace("Extraction completed Successfully...");
//		logger.trace("Totally "+start+" pages extracted... ");
//		
//		}
//		
//		
	}
}
