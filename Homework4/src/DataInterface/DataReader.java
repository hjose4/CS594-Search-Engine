package DataInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import Util.ReadWriteFile;
import Util.StopWord;
import model.Page;
import model.PageRank;
import model.TDF;


 
public class DataReader {
    private int totalCountries;
    private String data = "Afghanistan, Albania, Zimbabwe";
    private List<String> countries;
    
    public static HashMap<String, ArrayList<TDF>> IndexMap = new HashMap<String, ArrayList<TDF>>();
    
    public static HashMap<String, PageRank> RankMap = new HashMap<String, PageRank>();
    
    public static HashMap<String, Page> UrlsMap = new HashMap<String, Page>();
    
    public DataReader() {
        countries = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");
        
//        countries = readIndex();
        
        IndexMap = ReadWriteFile.readIndexFile();
        
        RankMap = ReadWriteFile.readRankFile();
        
        UrlsMap = ReadWriteFile.readUrlsFile();
        
        for(Entry<String, ArrayList<TDF>> m :IndexMap.entrySet()){
//            System.out.println(m.getKey()+" : "+m.getValue());
        	countries.add(m.getKey().trim());
        	
        }
         
//        while(st.hasMoreTokens()) {
//            countries.add(st.nextToken().trim());
//        }
        totalCountries = countries.size();
        System.out.println("Total Words ----->"+totalCountries);
    }
     
    public List<String> getData(String query) {
        String country = null;
        query = query.toLowerCase();
//        List<String> matched = new ArrayList<String>();
        SortedArrayList<String> matched = new SortedArrayList<String>();
        for(int i=0; i<totalCountries; i++) {
            country = countries.get(i).toLowerCase();
            if(country.startsWith(query)) {
                matched.insertSorted(countries.get(i));
                
            }
        }
        return matched;
    }
    
    public ArrayList<String> Search(String query) {
    	
//    	String country = null;
    	String input =null;
        query = query.toLowerCase();
        
        boolean flag = false;
        if (query.contains(" and "))
        {
        	flag = true;
        }
        
        ArrayList<String> matched = new ArrayList<String>();
        
        ArrayList<String> priority = new ArrayList<String>();
        
    	try {
			 input = StopWord.StopStem(query);
		
    	
    	StringTokenizer st = new StringTokenizer(input, " ");
    	
    	HashMap<String, Double> scoreMap = new HashMap();
    	
    	HashMap<String, Double> RankScore = new HashMap();
        
        
      while(st.hasMoreTokens()) {
    	  
//    	  getScore(st.nextToken().trim());
    	  
    	  String word = st.nextToken().trim();
    	  
    	  System.out.println("getScore -- input--> "+word);
      	
      	ArrayList<TDF> sites = new ArrayList<TDF>();
      	
      	sites = IndexMap.get(word);
      	
//      	HashMap<String, Double> scoreMap = new HashMap();
      	
      	for (TDF t : sites){
      		
      		String page = t.getId();
//      		System.out.println("input-->score : "+word+" Doc id --> "+page);
//      		System.out.println("input-->score : "+word+"Doc score --> "+t.getScore());
//      		scoreMap.compute(term, (k, v) -> v == null ? 1 : v + 1);
      		
      		if(scoreMap.containsKey(page)){
  			    // if the key has already been used,
  			    // we'll just grab the array list and add the value to it
      			Double pScore;
      			pScore = (Double) scoreMap.get(page);
//      			System.out.println("Doc pScore --> "+pScore);
      			pScore = pScore + t.getScore();
      			scoreMap.put(page, pScore);
      			if (flag)
      			{
      				priority.add(page);
      			}
//      			System.out.println("Doc pScore new--> "+scoreMap.get(page));
  			} else {
  			    // if the key hasn't been used yet,
  			    // we'll create a new ArrayList<String> object, add the value
  			    // and put it in the array list with the new key
//  				System.out.println("Added in score map --> "+page+" - score :" +t.getScore());
  				scoreMap.put(page, t.getScore());
  			}
      		
      		
      		
      	}
      	
      	

      }
      
//      System.out.println("scoreMap --> "+scoreMap.values());
    	
//    	System.out.println("scoreMap max --> "+Collections.max(scoreMap.values()));
    	
//    	Collections.max(scoreMap.values());
    	
    	Double max_index = Collections.max(scoreMap.values());
    	
    	if (flag)
    	{
    		for (String s : priority)
    		{
    		if(scoreMap.containsKey(s)){
  			    // if the key has already been used,
  			    // we'll just grab the array list and add the value to it
      			Double pScore;
      			pScore = (Double) scoreMap.get(s);
//      			System.out.println("Doc pScore --> "+pScore);
      			pScore = pScore + max_index;
      			scoreMap.put(s, pScore);
    		}
    		}
    		max_index = Collections.max(scoreMap.values());
    	}
      
      for (Entry<String, Double> entry : scoreMap.entrySet())
		{
    	  
    	  	PageRank pr = RankMap.get(entry.getKey());
    	  	
//			System.out.println("key : "+entry.getKey()+" rank :"+entry.getValue()+" PageRank : "+pr.getRank() );
			
			Double nRank= entry.getValue()/max_index;
			
//			System.out.println("key : "+entry.getKey()+" rank :"+entry.getValue()+"/"+max_index+" Nrank :"+nRank+" PageRank : "+pr.getRank() );
			
//			calculateRank(nRank,pr.getRank() );
			
			System.out.println("Page name : "+entry.getKey()+" TFIDF : "+nRank+" PageRank : "+pr.getRank()+" Rank :"+calculateRank(nRank,pr.getRank() ));
			
			RankScore.put(entry.getKey(), calculateRank(nRank,pr.getRank() ));
		}
      
      
//      	System.out.println("Sorted RankScore--->"+sortByComparator(RankScore));
      	
      	matched = sortByComparator(RankScore);
      	
//      	matched.addAll(sortByComparator(RankScore).keySet());
      	
      	
      	
      	System.out.println("Sorted pages matched--->"+matched);
      	
//      	sortByComparator(RankScore);
      	
//      RankMap.get(word);
      
        
        
//        for(int i=0; i<totalCountries; i++) {
//            country = countries.get(i).toLowerCase();
//            if(country.startsWith(query)) {
//                matched.add(countries.get(i));
//            }
//        }
        
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return  matched;
    }
    
    
    public static Double calculateRank(Double iRank, Double pRank)
    {
    	Double Rank;
    	
//    	iRank = iRank/2;
//    	
//    	pRank = pRank/2;
    	
    	Rank = (iRank*0.7) + (pRank*0.3);
    	
//    	System.out.println("Rank----> "+Rank);
    	
    	return Rank;
    }
    
    
    public static ArrayList<String> readIndex()
    {
   	JSONParser parser = new JSONParser();
   	
   	ArrayList<String> list  = new ArrayList<String>();
   	
   	

   	try {
   			
   		File file = new File("C:/DRIVE/big.json");

   		Object obj = parser.parse(new FileReader(file));
   			
   		JSONObject jsonObject = (JSONObject) obj;
   	
   		
   		
   		HashMap<String,ArrayList<JSONObject>> map = (HashMap<String, ArrayList<JSONObject>>) jsonObject.get("wordlist");
   		
   		
   		for (Entry<String, ArrayList<JSONObject>> entry : map.entrySet()) {
   			list.add(entry.getKey());
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().size());
			

			for (JSONObject o : entry.getValue())
			{
				TDF t = new TDF ();
				
				HashMap<String,ArrayList> map1 = (HashMap<String, ArrayList>) o.entrySet().iterator().next();
				
				for (Entry<String, ArrayList> entry1 : map1.entrySet()) {
//		   			list.add(entry.getKey());
					System.out.println("Key : " + entry1.getKey() + " Value : " + entry1.getValue().size());
					
//				System.out.println("get id --->"+t.getId());
//				System.out.println("get count --->"+t.getCount());
//				System.out.println("get score --->"+t.getScore());
			}
		}
   		}
   		
   		
   		int sno =1;
   		for (String s : list)
   		{
   			System.out.println(sno+" : "+s);
   			sno++;
   		}
   		
////   		String name = file.getName();
////   		int pos = name.lastIndexOf(".");
////   		if (pos > 0) {
////   		    name = name.substring(0, pos);
////   		}
//   		
//   		// loop array
//   		JSONArray msg = (JSONArray) jsonObject.get(name);
//   		Iterator<JSONObject> iterator = msg.iterator();
//   		
//   		while (iterator.hasNext()) {
//   			
//   			String key = "";
//   			int value;
//
//   			JSONObject wordSet = (JSONObject) iterator.next();
//
//   			key =wordSet.keySet().toArray()[0].toString();
//   			
//   			value = Integer.parseInt(wordSet.values().toArray()[0].toString());
//   			
//   			TDF td =new TDF();
//   			td.setsNO(Integer.parseInt(name));
//   			td.setCount(value);
//   			td.setId(null);
//   			
//   			Index i = new Index();
//   			i.setWord(key);
//   			i.setSite(td);
//   			
//   			list.add(i);
////   			System.out.println(i.getWord()+" : "+i.getSite().getsNO()+" : "+i.getSite().getCount());
//   			
//   		}
//   		
//   		
//   			
   	} catch (FileNotFoundException e) {
   		e.printStackTrace();
   	} catch (IOException e) {
   		e.printStackTrace();
   	} catch (ParseException e) {
   		e.printStackTrace();
   	}
   	
   	return list;
   	
   }
    
    
    private static ArrayList <String> sortByComparator(Map<String, Double> unsortMap) {

    	ArrayList <String> page = new ArrayList <String>();
    	
		// Convert Map to List
		List<Map.Entry<String, Double>> list = 
			new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
                                           Map.Entry<String, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
			page.add(entry.getKey());
//			System.out.println(entry.getKey());
		}
		System.out.println("Sorted RankScore--->"+sortedMap);
//		return sortedMap;
		return page;
	}
    
}
class SortedArrayList<T> extends ArrayList<T> {

    @SuppressWarnings("unchecked")
    public void insertSorted(T value) {
        add(value);
        Comparable<T> cmp = (Comparable<T>) value;
        for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--)
            Collections.swap(this, i, i-1);
    }
}