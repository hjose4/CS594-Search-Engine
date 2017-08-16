package crawler;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MultiThreading.ThreadManager;
import model.Link;

public class CrawlerManager {
	
	final static Logger logger = LogManager.getLogger(CrawlerManager.class.getName());
	
	public static Queue<Link> UrlsToCrawl = new LinkedList<Link>(); 
	public static Queue<Link> UrlsCrawled = new LinkedList<Link>(); 
	
	public static int counter = 0;
	public static int o_count = 0; 
	public static int n_count = 0;
	

	public static void main(String[] args) throws InterruptedException {
		
		Link InitialUrl = new Link();		
		InitialUrl.setLink("http://www.calstatela.edu");
		InitialUrl.setLevel(0);
		int counter = 0;
		
		Crawler c1 = new Crawler(InitialUrl, UrlsToCrawl, UrlsCrawled);
//		count();
		ThreadManager t1 = new ThreadManager("t1",c1);
		t1.start();
		Crawler c2 = new Crawler();
		ThreadManager t2 = new ThreadManager("t2",c2);
		t2.start();
//		count();
		Crawler c3 = new Crawler();
		ThreadManager t3 = new ThreadManager("t3",c3);
		t3.start();
//		count();
		Crawler c4 = new Crawler();
		ThreadManager t4 = new ThreadManager("t4",c4);
		t4.start();
//		count();
		Crawler c5 = new Crawler();
		ThreadManager t5 = new ThreadManager("t5",c5);
		t5.start();
		Crawler c6 = new Crawler();
		ThreadManager t6 = new ThreadManager("t6",c6);
		t6.start();
		Crawler c7 = new Crawler();
		ThreadManager t7 = new ThreadManager("t7",c7);
		t7.start();
		Crawler c8 = new Crawler();
		ThreadManager t8 = new ThreadManager("t8",c8);
		t8.start();
		Crawler c9 = new Crawler();
		ThreadManager t9 = new ThreadManager("t9",c9);
		t9.start();
		Crawler c10 = new Crawler();
		ThreadManager t10 = new ThreadManager("t10",c10);
		t10.start();

		Thread.sleep(10000); 
		
		while (UrlsToCrawl.peek()!= null )
		{
			Thread.sleep(1000); 
		}

		for (Link l : UrlsCrawled)
		{

			logger.trace("crawled : "+l.getLevel()+" "+l.getLink());
			counter++;

		}
		logger.trace("Crawling completed Successfully...");
		logger.trace("Totally "+counter+" pages crawled... ");
		
		

	}
	
	public static void count()
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		int temp;
		temp =counter;
		counter =0;
		o_count=0;
		n_count=0;
		
		
		for(Link l : UrlsToCrawl)
		{
			l.getLink();
//			System.out.println(l.getLevel()+" "+l.getLink());
			counter++;
			if (l.getLevel() == 1)
			{
				o_count++;
			}
			else
			{
				n_count++;
			}
		}
		System.out.println("total "+counter+" new links. previous "+o_count+" now "+n_count+" diff "+temp );
		System.out.println("-------------------------------------------------------------------");

	}
	

}
