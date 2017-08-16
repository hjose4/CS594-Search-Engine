package index;

import java.io.File;
import java.util.ArrayList;

import org.jsoup.select.Elements;

import MultiThreading.ExtractThread;
import Util.Extract;
import Util.readJSON;
import Util.writeJSON;
import model.Index;
import model.Raw;
import model.TDF;

public class Indexer {
	
	
	public static void PollContinuously()
	{	
		Elements ExtLinks;
			
		File toInd ;
		toInd = IndexManager.UrlsToIndex.poll();
		
		if (toInd!=null)
		{
//			Extract e = new Extract(toExt);
//			ExtractThread t = new ExtractThread("Extract Thread ",e);
//			t.start();
//			getDetails(toExt);
			FileDetails(toInd);
		}
		
//		logger.exit();
	}
	
	
	public static void FileDetails(File input )
	{
		ArrayList<Index> i = new ArrayList<Index>();
		
		i = readJSON.readIndex(input.getAbsolutePath());
		
		
		constructIndex(i);
		
		IndexManager.UrlsIndexed.add(input);
		
	}
	
	public static void constructIndex(ArrayList<Index> input)
	{
		
		
		for (Index i : input)
		{
			String key = i.getWord();
			
			ArrayList<TDF> list;
			if(IndexManager.m.containsKey(key)){
			    // if the key has already been used,
			    // we'll just grab the array list and add the value to it
			    list = IndexManager.m.get(key);
			    list.add(i.getSite());
			} else {
			    // if the key hasn't been used yet,
			    // we'll create a new ArrayList<String> object, add the value
			    // and put it in the array list with the new key
			    list = new ArrayList<TDF>();
			    list.add(i.getSite());
			    IndexManager.m.put(key, list);
			}
				
		}

	}

}
