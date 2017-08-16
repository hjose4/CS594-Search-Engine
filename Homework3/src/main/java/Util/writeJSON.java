package Util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import model.Doc;
import model.IndexFile;
import model.Page;
import model.PageRank;
import model.TDF;
import model.TF;
import rank.Ranking;


public class writeJSON {
	
     public static void main(String[] args) {
     }
     
     public static void execute(Page p)
     {

	JSONObject obj = new JSONObject();
	obj.put("name", "mkyong.com");
//	obj.put("age", new Integer(100));
//
//	JSONArray list = new JSONArray();
//	list.add("msg 1");
//	list.add("msg 2");
//	list.add("msg 3");

	obj.put("messages", p);

	try {
		
		File file = new File("C:/DRIVE/test.json");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file, true);
		fileWriter.write(obj.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	//System.out.print(obj);

     }
     
     public static void execute(Doc d)
     {

	JSONObject obj = new JSONObject();
	
	
	
	JSONArray list = new JSONArray();
	
	for( TF t : d.getWordList())
	{
		JSONObject obj1 = new JSONObject();
		obj1.put(t.getWord(), t.getCount());
		list.add(obj1);
	}
	
	obj.put( d.getId(), list);
//	obj.put("age", new Integer(100));

//	JSONArray list = new JSONArray();
//	list.add("msg 1");
//	list.add("msg 2");
//	list.add("msg 3");
//
//	obj.put("messages", list);

	try {
		
		
		File file = new File("C:/DRIVE/Doc/"+d.getId()+".json");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file, true);
		fileWriter.write(obj.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	//System.out.print(obj);

     }
     
    public static void execute(ConcurrentMap<String, ArrayList<TDF>> input, int N)
    {
    	
//    	System.out.println("input  ....."+input);
    	
    	HashMap<String, ArrayList<TDF>> indexMap = new HashMap();
    	
    	
    	
    	JSONObject obj = new JSONObject();
    	 
    	Iterator it = input.entrySet().iterator();
    	ArrayList<TDF> list = new ArrayList<TDF>();
    	int counter = 0;
			
		while (it.hasNext()) {
			
//			IndexFile i = new IndexFile();
			
			String word = "";
			int tf;
			int df;
			Double idf;
			Double wtd;
			
			try 
	    	{
			Map.Entry pairs = (Map.Entry) it.next();
				
			
			word = (String) pairs.getKey();
			list = (ArrayList<TDF>) pairs.getValue();
			
			df = list.size();
			idf = Ranking.idf(N, df);
			
				
			JSONArray jlist = new JSONArray();
			
			ArrayList<TDF> maplist = new ArrayList<TDF>();
			
				
			for( TDF t : list)
			{
			
				
				
				tf = t.getCount();
//				JSONObject obj1 = new JSONObject();
				JSONArray tlist = new JSONArray();
				tlist.add(t.getId());
				tlist.add(tf);
				wtd = Ranking.tfidf(tf, idf);
				tlist.add(wtd);
//				obj1.put(t.getId(),tlist); // 
				jlist.add(tlist);
									
				TDF maptdf = new TDF(t.getsNO(),t.getId(),tf,wtd);
				maplist.add(maptdf);
			}		
			obj.put(word, jlist);
			indexMap.put(word, maplist);
			
			
			
			
			
			
			
			
			
	    	 }catch (NullPointerException e)
	    	 {
	    		 System.out.println(word);
	    	 }
			
		}
    	 
		ReadWriteFile.writeIndexFile(indexMap);
		
		System.out.println("writeJSON --> Started");

//		try {   //write json
//			
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    		JsonParser jp = new JsonParser();
//    		JsonElement je = jp.parse(obj.toJSONString());
//    		String prettyJsonString = gson.toJson(je);
//	
//			FileWriter file = new FileWriter("C:/DRIVE/big.json");
//			file.write(prettyJsonString);
//			file.flush();
//			file.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			
//		}
		
		System.out.println("writeJSON --> Done");
		
    }
    
    public static void WriteJsonFile(HashMap<String, PageRank> map, Double max_rank)
    {
    	
    	JSONObject obj = new JSONObject();
    	
    	obj.put("max_rank", max_rank);
    		
    	
    	JSONArray list = new JSONArray();
    	for (Entry<String, PageRank> entry : map.entrySet())
		{
    		JSONArray jlist = new JSONArray();
    		jlist.add(entry.getValue().getCurr());
    		jlist.add(entry.getValue().getRank());
    		obj.put(entry.getKey(), jlist);
//			entry.getValue().setRank(entry.getValue().getRank()/max_rank);
		}
    	
    	try {   //write json
    		
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		JsonParser jp = new JsonParser();
    		JsonElement je = jp.parse(obj.toJSONString());
    		String prettyJsonString = gson.toJson(je);
    		
			FileWriter file = new FileWriter("C:/DRIVE/rank.json");
			file.write(prettyJsonString);
			file.flush();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
    }
    
    public static void WriteJsonFile(HashMap<String, Page> map)
    {
    	
    	JSONObject obj = new JSONObject();
    	
  		
    	for (Entry<String, Page> entry : map.entrySet())
		{
//    		JSONArray jlist = new JSONArray();
//    		jlist.add(entry.getValue().getCurr());
//    		jlist.add(entry.getValue().getRank());
    		obj.put(entry.getKey(), entry.getValue().getUrl());
//			entry.getValue().setRank(entry.getValue().getRank()/max_rank);
		}
    	
    	try {   //write json
    		
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		JsonParser jp = new JsonParser();
    		JsonElement je = jp.parse(obj.toJSONString());
    		String prettyJsonString = gson.toJson(je);
    		
			FileWriter file = new FileWriter("C:/DRIVE/urls.json");
			file.write(prettyJsonString);
			file.flush();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
    }

}