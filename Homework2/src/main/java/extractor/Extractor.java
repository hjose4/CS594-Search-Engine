package extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import crawler.Crawler;
import crawler.CrawlerManager;
import database.DbClient;
import model.Link;
import model.Raw;

public class Extractor {
	
	
	public static Queue<Raw> UrlsToExtract;
	
	final static Logger logger = LogManager.getLogger(Extractor.class.getName());
	
	private static final DbClient connect = new DbClient(CrawlerManager.Coll+"_ext");

	public Extractor(Queue<Raw> UrlsToExtract) {
		
		this.UrlsToExtract = UrlsToExtract;

	}
	
	
	public Extractor() {

	}
	
	
	
	
	public static synchronized void PollContinuously()
	{

//		logger.entry();		
		
		String inputUrl ="";
		int level;
		Elements ExtLinks;
			
		Raw toExt = new Raw();
		toExt = UrlsToExtract.poll();
		
		if (toExt!=null)
		{
			
			getDetails(toExt);
			
		}
		
//		logger.exit();
	}
	
	
	public static void getDetails(Raw input)
	{
		String inputUrl ="";
		String ViewSource ="";
		String CrawlDate ="";
		
		inputUrl = input.getUrl();
		ViewSource = input.getSource();
		CrawlDate = input.getDateTime();
		
		try {
			
			logger.trace("Extracting : "+inputUrl);
						
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();;
			
			String filename = URLEncoder.encode(inputUrl, "UTF-8");
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
						
			WriteFile(s+"/data/"+filename+".html", ViewSource);
			
						
			Document source = Jsoup.parse(ViewSource);
			
			String title = "";
			if(source.title()!=null)
				title = source.title();
			String Bodytext = "";
			if(source.body().text()!=null)
				Bodytext = source.body().text();
			
			String description = "";
			try
			{
			if(source.select("meta[name=description]").get(0)!=null)
				description = source.select("meta[name=description]").get(0).attr("content");
			}catch(IndexOutOfBoundsException e){
				logger.error("description not found");
			}
			
			String keywords = "";
			try
			{
			if(source.select("meta[name=keywords]").first()!=null)
				keywords = source.select("meta[name=keywords]").first().attr("content");
			}catch(NullPointerException e){
			}
			
			
			
			Elements media = source.select("[src]");
			Elements imports = source.select("link[href]");
		    Elements links = source.select("a[href]");
		    Elements metadata = source.select("META");
		    
		    
		    ArrayList<String> Img = new ArrayList<String>();
		    ArrayList<String> Script = new ArrayList<String>();
		    ArrayList<String> Imports = new ArrayList<String>();
		    ArrayList<String> Links = new ArrayList<String>();
		    ArrayList<String> Metadata = new ArrayList<String>();
		    
		    
		    for (Element src : media) {
		    	if (src!= null && !src.equals(""))
	        	{
		    		if (src.tagName().equals("img"))
		            {
		                Img.add(src.attr("abs:src"));
		            }
		            else
		            {
		            	Script.add(src.attr("abs:src"));
		            }
	        	}   	
	           
	        }
		    
		    
		    for (Element link : imports) {
		    	
		    	if (link!= null && !link.equals(""))
	        	{
		    		Imports.add(link.attr("abs:href"));
	        	}
	            
	        }
		    
		    for (Element link : links) {
	        	if (link!= null && !link.equals(""))
	        	{
	        	
	        		Links.add(link.attr("abs:href"));
	        	
	        	}
	        }
		     
		    
		    for (Element meta : metadata)
		    {
		    	if (meta!= null && !meta.equals(""))
	        	{
		    		
		    		for (Element m : meta.getAllElements())
		    		{
		    			if (m!= null && !m.equals(""))
		    			{
		    				Metadata.add(m.toString());
		    			}
		    			
		    		}
			        	
	        	}
		    }
		 
		
	        connect.Insert(inputUrl, title, s+"/data/"+filename+".html", CrawlDate, Bodytext, description, keywords, Metadata, Img, Script, Imports, Links, dateFormat.format(date).toString());
			
			
		} catch (IllegalArgumentException e) {
	    	
	    	logger.error(" IllegalArgumentException : Must supply a valid URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
	    	logger.error(e1);
			e1.printStackTrace();
		}
		
		
	}
	
	public static void WriteFile(String file, String ViewSource)
	{
		BufferedWriter bw;
		try {

			bw = new BufferedWriter(new FileWriter(file));
		
		bw.write(ViewSource);
		bw.close();
		
		} catch(FileNotFoundException e)
		{
			logger.error(e);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}

	
	
	

}
