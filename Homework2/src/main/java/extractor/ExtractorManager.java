package extractor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MultiThreading.CrawlerThreadManager;
import MultiThreading.ExtractorThreadManager;
import crawler.Crawler;
import crawler.CrawlerManager;
import database.DbClient;
import model.Link;
import model.Raw;

public class ExtractorManager {
	
	final static Logger logger = LogManager.getLogger(ExtractorManager.class.getName());
	
	private static final DbClient connect = new DbClient(CrawlerManager.Coll);
	
	public static Queue<Raw> UrlsToExtract = new LinkedList<Raw>(); 
	
	public static int start = 0;
	
	public static int end = 0;
	 
	
	public static boolean status = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		execute();
        

	}
	
	public static void RetriveData()
	{
		
		ArrayList<Raw> list = new ArrayList<Raw>();
		
		list = connect.Retrive(start);
		
		if (list!= null && !list.isEmpty())
		{
			int count = list.size();
			
			for (Raw r : list)
			{
				UrlsToExtract.add(r);
			}
			
			logger.trace("Records Retrived : "+count);
			
			start = start + count;			
			
			logger.trace("Total Records Retrived : "+start);
			
			logger.trace("Records added : "+UrlsToExtract.size());
		}
	}

	
	public static void execute()
	{
		
		
		RetriveData();
		
		
		Extractor e1 = new Extractor(UrlsToExtract);
		ExtractorThreadManager t11 = new ExtractorThreadManager("Ext Thread 11",e1);
		t11.start();
		
		Extractor e2 = new Extractor();
		ExtractorThreadManager t12 = new ExtractorThreadManager("Ext Thread 12",e2);
		t12.start();

		Extractor e3 = new Extractor();
		ExtractorThreadManager t13 = new ExtractorThreadManager("Ext Thread 13",e3);
		t13.start();

		Extractor e4 = new Extractor();
		ExtractorThreadManager t14 = new ExtractorThreadManager("Ext Thread 14",e4);
		t14.start();

		Extractor e5 = new Extractor();
		ExtractorThreadManager t15 = new ExtractorThreadManager("Ext Thread 15",e5);
		t15.start();
		
		Extractor e6 = new Extractor();
		ExtractorThreadManager t16 = new ExtractorThreadManager("Ext Thread 16",e6);
		t16.start();
		
		Extractor e7 = new Extractor();
		ExtractorThreadManager t17 = new ExtractorThreadManager("Ext Thread 17",e7);
		t17.start();
		
		Extractor e8 = new Extractor();
		ExtractorThreadManager t18 = new ExtractorThreadManager("Ext Thread 18",e8);
		t18.start();
		
		Extractor e9 = new Extractor();
		ExtractorThreadManager t19 = new ExtractorThreadManager("Ext Thread 19",e9);
		t19.start();
		
		Extractor e10 = new Extractor();
		ExtractorThreadManager t20 = new ExtractorThreadManager("Ext Thread 20",e10);
		t20.start();
		
				
		try {
		
		
		while (CrawlerManager.status)
		{
		Thread.sleep(4000);
		
		logger.trace("waiting for crawler to complete...");			
		
		RetriveData();
		
		Thread.sleep(6000);
		
		}
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RetriveData();
		
		while(UrlsToExtract.peek()!= null)
		{
			try {
				Thread.sleep(1000);
			
			
			if (UrlsToExtract.size()<100)
			{
				RetriveData();
			}
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (UrlsToExtract.size()==0)
		{
			status = false;
		}
		
		if (!CrawlerManager.status && !status)
		{
			
		logger.trace("Extraction completed Successfully...");
		logger.trace("Totally "+start+" pages extracted... ");
		
		}
		
		
	}
}
