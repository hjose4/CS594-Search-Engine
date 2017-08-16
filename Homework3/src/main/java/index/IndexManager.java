package index;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MultiThreading.IndexerThreadManager;
import Util.readJSON;
import Util.writeJSON;
import core.index;
import extractor.ExtractorManager;
import model.Index;
import model.Page;
import model.Raw;
import model.TDF;


public class IndexManager {
	
//	public static Queue<String> UrlsToIndex;
	
//	public static Queue<String> UrlsIndexed;
	

	final static Logger logger = LogManager.getLogger(ExtractorManager.class.getName());
	
	public static boolean status = true;
	
//	BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	
	public static ConcurrentMap<String, ArrayList<TDF>> m = new ConcurrentHashMap<>();
	
	public static ConcurrentLinkedQueue<File> UrlsToIndex = new ConcurrentLinkedQueue<File>();
	
	public static ConcurrentLinkedQueue<File> UrlsIndexed = new ConcurrentLinkedQueue<File>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		executei("C:/DRIVE/Doc - Copy");
		executei("C:/DRIVE/Doc");
		

	}
	
	public static void executei(String path)
	{
		
		
		File input = new File(path);
		
//		while (input.listFiles()!= null )
//		{
			
			int counter = 0;
			
				File[] subfolders = input.listFiles();
				for(File filename : subfolders){
					UrlsToIndex.add(filename);
//					FileDetails(filename);
//					System.out.println(++counter);
				}
				
				
				execute();
				
				
//
//				System.out.println("done");
//				writeJSON.execute(m);
//				System.out.println("done");
			
	}
	
	public static void execute()
	{
		

		System.out.println("UrlsToIndex size : "+UrlsToIndex.size());
		
		
		Indexer e1 = new Indexer();
		IndexerThreadManager t1 = new IndexerThreadManager("Ind Thread 1",e1);
		t1.start();
		
		Indexer e2 = new Indexer();
		IndexerThreadManager t2 = new IndexerThreadManager("Ind Thread 2",e2);
		t2.start();

		Indexer e3 = new Indexer();
		IndexerThreadManager t3 = new IndexerThreadManager("Ind Thread 3",e3);
		t3.start();

		Indexer e4 = new Indexer();
		IndexerThreadManager t4 = new IndexerThreadManager("Ind Thread 4",e4);
		t4.start();

		Indexer e5 = new Indexer();
		IndexerThreadManager t5 = new IndexerThreadManager("Ind Thread 5",e5);
		t5.start();
		
		Indexer e6 = new Indexer();
		IndexerThreadManager t6 = new IndexerThreadManager("Ind Thread 6",e6);
		t6.start();
		
		Indexer e7 = new Indexer();
		IndexerThreadManager t7 = new IndexerThreadManager("Ind Thread 7",e7);
		t7.start();
		
		Indexer e8 = new Indexer();
		IndexerThreadManager t8 = new IndexerThreadManager("Ind Thread 8",e8);
		t8.start();
		
		Indexer e9 = new Indexer();
		IndexerThreadManager t9 = new IndexerThreadManager("Ind Thread 9",e9);
		t9.start();
		
		Indexer e10 = new Indexer();
		IndexerThreadManager t10 = new IndexerThreadManager("Ind Thread 10",e10);
		t10.start();
		
		
		
		
		
		while(UrlsToIndex.peek()!= null)
		{
			try {
				Thread.sleep(1000);
				
				logger.trace("to go :"+UrlsToIndex.size());
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (UrlsToIndex.size()==0)
		{
			status = false;
		}
		
		
		
		System.out.println("done");
		int N = UrlsIndexed.size();
		System.out.println("N --->"+N);
		writeJSON.execute(m,N);
		System.out.println("done");
	}
	
	
	

}
