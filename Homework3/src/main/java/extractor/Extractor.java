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
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import MultiThreading.ExtractThread;
import MultiThreading.ExtractorThreadManager;
import Util.Extract;
import model.Page;
import model.Raw;



public class Extractor {
	
	
	public static Queue<Raw> UrlsToExtract;
	
	public static ConcurrentLinkedQueue<Page> UrlsExtracted;
	
	final static Logger logger = LogManager.getLogger(Extractor.class.getName());

	public Extractor(Queue<Raw> UrlsToExtract, ConcurrentLinkedQueue<Page> UrlsExtracted) {
		
		this.UrlsToExtract = UrlsToExtract;
		this.UrlsExtracted = UrlsExtracted;		

	}
	
	
	public Extractor() {

	}
	
	
	
	
	public static synchronized void PollContinuously()
	{	
		Elements ExtLinks;
			
		Raw toExt = new Raw();
		toExt = UrlsToExtract.poll();
		
		if (toExt!=null)
		{
			Extract e = new Extract(toExt);
			ExtractThread t = new ExtractThread("Extract Thread ",e);
			t.start();
//			getDetails(toExt);
		}
		
//		logger.exit();
	}
	
	
	public static void getDetails(Raw input)
	{
		int sNo;
		String file ="";
		String path ="";
		
		sNo = input.getsNo();
		file = input.getFileName();
		path = input.getFilePath();
		
		try {
		
			Page p = new Page();
			
			logger.trace("Extracting : "+path);
						
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date date = new Date();
			
			p.setsNo(sNo);
			p.setId(MD5(file));
			p.setUrl(file);
			p.setPath(path);
			System.out.println("file :"+file+" md5 :"+MD5(file));
			
//			String filename = URLEncoder.encode(inputUrl, "UTF-8");
//			Path currentRelativePath = Paths.get("");
//			String s = currentRelativePath.toAbsolutePath().toString();
//						
//			WriteFile(s+"/data/"+filename+".html", ViewSource);
//			
//						
//			Document source = Jsoup.parse(ViewSource);
			
			File f = new File(path);
			Document source = Jsoup.parse(f, "UTF-8");
			
			
			String title = "";
			if(source.title()!=null)
				title = source.title();
			p.setTitle(title);
			
			String Bodytext = "";
			if(source.body().text()!=null)
				Bodytext = source.body().text();
			p.setBodytext(Bodytext);
			
			
			String description = "";
			try
			{
			if(source.select("meta[name=description]").get(0)!=null)
				description = source.select("meta[name=description]").get(0).attr("content");
			
			}catch(IndexOutOfBoundsException e){
				logger.error("description not found");
			}
			p.setDescription(description);
			
			String keywords = "";
			try
			{
			if(source.select("meta[name=keywords]").first()!=null)
				keywords = source.select("meta[name=keywords]").first().attr("content");
			}catch(NullPointerException e){
			}
			p.setKeywords(keywords);
			
			
			
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
		    			if (!src.attr("abs:src").equals(""))
		    				Img.add(src.attr("abs:src"));
		            }
		            else
		            {
		            	if (!src.attr("abs:src").equals(""))
		            		Script.add(src.attr("abs:src"));
		            }
	        	}   	
	           
	        }
		    
		    p.setImg(Img);
		    p.setScript(Script);
		    
		    
		    for (Element link : imports) {
		    	
		    	if (link!= null && !link.equals(""))
	        	{
		    		if (!link.attr("abs:href").equals(""))
		    			Imports.add(link.attr("abs:href"));
	        	}
	            
	        }
		    
		    p.setImports(Imports);
		    
		    for (Element link : links) {
	        	if (link!= null && !link.equals(""))
	        	{
	        	
	        		if (!link.attr("abs:href").equals(""))
	        		{
	        			System.out.println(MD5(link.attr("abs:href")));
	        		Links.add(MD5(link.attr("abs:href")));
	        		}
	        	
	        	}
	        }
		    p.setLinks(Links);
//		     
//		    
//		    for (Element meta : metadata)
//		    {
//		    	if (meta!= null && !meta.equals(""))
//	        	{
//		    		
//		    		for (Element m : meta.getAllElements())
//		    		{
//		    			if (m!= null && !m.equals(""))
//		    			{
//		    				Metadata.add(m.toString());
//		    			}
//		    			
//		    		}
//			        	
//	        	}
//		    }
//		 
//		
		
		    UrlsExtracted.add(p);
		    
		} catch (IllegalArgumentException e) {
	    	
	    	logger.error(" IllegalArgumentException : Must supply a valid URL: " + path);
	        e.printStackTrace();
	        
	    }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
//	
//	public static void WriteFile(String file, String ViewSource)
//	{
//		BufferedWriter bw;
//		try {
//
//			bw = new BufferedWriter(new FileWriter(file));
//		
//		bw.write(ViewSource);
//		bw.close();
//		
//		} catch(FileNotFoundException e)
//		{
//			logger.error(e);
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error(e);
//		}
//	}
//
//	
//	
//	

}
