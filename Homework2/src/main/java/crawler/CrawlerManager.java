package crawler;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MultiThreading.CrawlerThreadManager;
import MultiThreading.ExtractorThread;
import extractor.ExtractorManager;
import model.Link;

public class CrawlerManager {
	
	final static Logger logger = LogManager.getLogger(CrawlerManager.class.getName());
	
	public static Queue<Link> UrlsToCrawl = new LinkedList<Link>();   //queue for adding links in BFS
	public static ArrayList<Link> UrlsCrawled = new ArrayList<Link>();  // queue for crawled url for records
	public static ArrayList<String> UrlsSeen = new ArrayList<String>();  // list for checking circular ref.
	
	public static String Coll ="cs454";    // Collection Name
	
	public static int depth = 0;
	
	public static boolean doExtract =false;
	
	
	public static boolean status = true;
	

	public static void main(String[] args) throws InterruptedException {
		
//		depth = Integer.parseInt(args[1]) ;
//		String arg = args[3];
//		
//		if( args.length>4)
//
//		{
//			if(args[4].trim().equals("-e"))
//			{
//				doExtract = true;
//			}
//		}
		
		depth = 1 ;
		String arg = "http://www.calstatela.edu";
		doExtract = true;
		
		
		
		System.out.println("depth : "+depth);
		System.out.println("url : "+arg);
		
		Link InitialUrl = new Link();		
		InitialUrl.setLink(arg);
		InitialUrl.setLevel(0);
		int counter = 0;
		
		//Initializing constructor
		Crawler c1 = new Crawler(InitialUrl, UrlsToCrawl, UrlsCrawled, UrlsSeen);
		CrawlerThreadManager t1 = new CrawlerThreadManager("Crawl Thread 1",c1);
		t1.start();
		
		// creating threads.
		Crawler c2 = new Crawler();
		CrawlerThreadManager t2 = new CrawlerThreadManager("Crawl Thread 2",c2);
		t2.start();

		Crawler c3 = new Crawler();
		CrawlerThreadManager t3 = new CrawlerThreadManager("Crawl Thread 3",c3);
		t3.start();

		Crawler c4 = new Crawler();
		CrawlerThreadManager t4 = new CrawlerThreadManager("Crawl Thread 4",c4);
		t4.start();

		Crawler c5 = new Crawler();
		CrawlerThreadManager t5 = new CrawlerThreadManager("Crawl Thread 5",c5);
		t5.start();
		
		Crawler c6 = new Crawler();
		CrawlerThreadManager t6 = new CrawlerThreadManager("Crawl Thread 6",c6);
		t6.start();
		
		Crawler c7 = new Crawler();
		CrawlerThreadManager t7 = new CrawlerThreadManager("Crawl Thread 7",c7);
		t7.start();
		
		Crawler c8 = new Crawler();
		CrawlerThreadManager t8 = new CrawlerThreadManager("Crawl Thread 8",c8);
		t8.start();
		
		Crawler c9 = new Crawler();
		CrawlerThreadManager t9 = new CrawlerThreadManager("Crawl Thread 9",c9);
		t9.start();
		
		Crawler c10 = new Crawler();
		CrawlerThreadManager t10 = new CrawlerThreadManager("Crawl Thread 10",c10);
		t10.start();

		Thread.sleep(10000); 
		
		
		
		if(doExtract)
		{
			logger.trace("Starting Extractor process...");
			ExtractorManager ex = new ExtractorManager();
			ExtractorThread ET = new ExtractorThread("Extractor Thread et",ex);
			ET.start();
		}
		
		
		
//		while (UrlsToCrawl.peek()!= null )
//		{
//			Thread.sleep(1000); 
//		}
//		
//		logger.trace("waiting...");
//		
//		if (UrlsToCrawl.size()==0)
//		{
//			status = false;
//		}
		
		while (UrlsToCrawl.peek()!= null )
		{
			
			logger.trace("Crawling..."+UrlsToCrawl.size()+" to go...");
			Thread.sleep(5000);
		}
		
		if (UrlsToCrawl.size()==0)
		{
			logger.trace("crawler que is empty..."+UrlsToCrawl.size());
			status = false;
		}

		for (Link l : UrlsCrawled)
		{

			logger.trace("crawled : "+l.getLevel()+" "+l.getLink());
			counter++;

		}
		logger.trace("Crawling completed Successfully...");
		logger.trace("Totally "+counter+" pages crawled... ");
		
		

	}
	
	
	public static void end()
	{
		CrawlerManager.status= false;
	}
	
	

}
