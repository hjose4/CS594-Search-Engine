package Util;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.json.simple.JSONArray;

import model.Doc;
import model.Page;
import model.TF;





public class StopWord {
	
	public static void main(String[] args) {
		
		String inp ="My interest about parallel computing dates since my undergraduate school, just one or two years after Google’s paper was published about how to make efficient data processing. From that time on, I was wondering how they manage to index “the web”. As I started learning the API and the HDFS, as well as exploring the implementation of the TF-IDF algorithm, as explained by the Cloudera training. I started this implementation after I implemented the InvertedIndex example using both the Hadoop 0.18 and the 0.20.1 APIs. The parts of my experiences are defined as follows";
		
		try {
			System.out.println(wordCount(StopStem(inp)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void execute(Page p){
		
		String text ="";
		
		text = p.getTitle()+" "+p.getDescription()+" "+p.getKeywords()+" "+p.getBodytext(); 
		
		Map<String, Integer> words = new HashMap<String, Integer>();
		
		try {
			words = wordCount(StopStem(text));
			
			Doc d = new Doc();
			
			d.setsNo(p.getsNo());
			d.setId(p.getId());
			
			Iterator it = words.entrySet().iterator();
			ArrayList<TF> list = new ArrayList<TF>();
			
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				
				String word = "";
				int count;
				
				word = (String) pairs.getKey();
				count = Integer.parseInt(pairs.getValue().toString());
				
				TF t = new TF(word, count);
				
				list.add(t);
				
				
			}
			
			d.setWordList(list);
			
			writeJSON.execute(d);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	
	public static String StopStem(String textFile) throws Exception {
		
		
		CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
	    TokenStream tokenStream = new StandardTokenizer();
	    ((Tokenizer) tokenStream).setReader(new StringReader(textFile.trim()));
	    tokenStream = new StopFilter(tokenStream, stopWords);
	    tokenStream = new PorterStemFilter(tokenStream);
	    StringBuilder sb = new StringBuilder();
	    CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
	    tokenStream.reset();
	    while (tokenStream.incrementToken()) {
	        String term = charTermAttribute.toString();
	        sb.append(term + " ");
	    }
	    return sb.toString();
	}
	
	
	public static ConcurrentMap wordCount(String inp){
		
		ConcurrentMap<String, Integer> m = new ConcurrentHashMap<>();
		
		TokenStream tokenStream = new StandardTokenizer();
	    ((Tokenizer) tokenStream).setReader(new StringReader(inp.trim()));
	    
	    CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
	    try {
	    	
			tokenStream.reset();
		
			while (tokenStream.incrementToken()) {
			    String term = charTermAttribute.toString().toLowerCase();
			    m.compute(term, (k, v) -> v == null ? 1 : v + 1);
			    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	
	
}
