package core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import Util.InputReader;
import Util.ReadWriteFile;
import Util.writeJSON;
import extractor.Extractor;
import extractor.ExtractorManager;
import link.LinkAnalysisManager;
import model.Page;
import model.Raw;

public class index {
	
	public static Queue<Raw> UrlsToExtract = new LinkedList<Raw>();
	
	public static ConcurrentLinkedQueue<Page> UrlsExtracted = new ConcurrentLinkedQueue<Page>();
	
	public static HashMap<String, Page> ExtractedMap = new HashMap<String, Page>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int input;
		
//		UrlsToExtract = InputReader.execute("C:/Users/genus_000/Downloads/wiki-small/en/articles/a");
//		UrlsToExtract = InputReader.execute("C:/Users/genus_000/Downloads/wiki-small/en");
		UrlsToExtract = InputReader.execute("C:/Users/genus_000/Downloads/munged/munged");
//		UrlsToExtract = InputReader.execute("C:/eclipse-jee-mars-1-win32-x86_64/WORKSPACE/Homework2/data");
//		UrlsToExtract = InputReader.execute("C:/Users/genus_000/Downloads/wiki-large/en/articles");
//		C:\Users\genus_000\Downloads\wiki-large\en\articles
		System.out.println(UrlsToExtract.size());
		
		input = UrlsToExtract.size();
		
//		Extractor e = new Extractor(UrlsToExtract,UrlsExtracted);
		
		ExtractorManager em = new ExtractorManager();
		
		em.execute();
		
		while (input - UrlsExtracted.size() > 0)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (UrlsExtracted.size()==input)
		System.out.println("ha ha comppleted"+UrlsExtracted.size());
		
		
		System.out.println("ha ha "+UrlsExtracted.size());
		
		
		for (Page p : UrlsExtracted)
		{
			
			ExtractedMap.put(p.getId(), p);
			
		}
		
		ReadWriteFile.writeUrlsFile(ExtractedMap);
		writeJSON.WriteJsonFile(ExtractedMap);  // write json 
		
		
		
		//Link analysis
		
		System.out.println("Link analysis Statrted...");
		
		LinkAnalysisManager lam = new LinkAnalysisManager(UrlsExtracted);
		lam.execute();
		
		
		//Indexing
		
		
		
		
		
		
	}
	
	public static synchronized void extracted(Page p){
		UrlsExtracted.add(p);
	}

}
