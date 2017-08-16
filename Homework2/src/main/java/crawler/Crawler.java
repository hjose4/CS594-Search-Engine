package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

import database.DbClient;
import model.Link;

public class Crawler {
	
	private static final DbClient connect = new DbClient(CrawlerManager.Coll);
	
	final static Logger logger = LogManager.getLogger(Crawler.class.getName());
	
	private String inputUrl;
	private int level;
	private Elements ExtLinks;
	private Document source;
	public static Queue<Link> UrlsToCrawl;
	public static ArrayList<Link> UrlsCrawled;
	public static ArrayList<String> UrlsSeen;
	
	public Crawler(Link InitialUrl, Queue<Link> UrlsToCrawl, ArrayList<Link> UrlsCrawled, ArrayList<String> UrlsSeen)
	{
		
		this.inputUrl = InitialUrl.getLink();
		this.level = InitialUrl.getLevel();
		this.UrlsToCrawl = UrlsToCrawl;
		this.UrlsCrawled = UrlsCrawled;
		this.UrlsSeen = UrlsSeen;
		
		ExtLinks = getInnerUrl(this.inputUrl);
		
		Link l1 = new Link();
		l1.setLevel(this.level);
		l1.setLink(this.inputUrl);
		UrlsCrawled.add(l1);
		
		if (ExtLinks!= null && !ExtLinks.isEmpty())
		{
		
			for (Element link : ExtLinks) {
//				logger.trace("link : " + ((Node)link).attr("abs:href"));
				
				Link l2 = new Link();
				l2.setLevel(this.level+1);
				l2.setLink(((Node)link).attr("abs:href"));
				UrlsToCrawl.add(l2);

			}
		}
	}
	
	public Crawler(Queue<Link> UrlsToCrawl, Queue<Link> UrlsCrawled)
	{
		

	}
	
	public Crawler()
	{
		
	}
	
	
	
	
	
	public static synchronized void PollContinuously()
	{
		int depth = CrawlerManager.depth;
//		logger.entry();		
		
		String inputUrl ="";
		int level;
		Elements ExtLinks;
			
		Link toCrawl = new Link();
		toCrawl = UrlsToCrawl.poll();
		if (toCrawl!=null)
		{
			inputUrl = toCrawl.getLink();
			level = toCrawl.getLevel();
			if (depth>=level)
			{
				boolean flag = true; // hash set find method
				
				if (UrlsCrawled.size()>0)
				{
					for(Link l : UrlsCrawled)
					{
						if(l.getLink().equals(inputUrl))
						{
							flag = false;	
							logger.trace("Circular reference : " +inputUrl);
						}
					}
				}
			
				if (flag)
				{
					logger.trace("link : " +inputUrl);
					
					ExtLinks = getInnerUrl(inputUrl);
					
					Link l1 = new Link();
					l1.setLevel(level);
					l1.setLink(inputUrl);
					UrlsCrawled.add(l1);
					UrlsSeen.add(inputUrl);
					
					if (ExtLinks!= null && !ExtLinks.isEmpty())
					{
					
						for (Element link : ExtLinks) {
	//						logger.trace("link : " + ((Node)link).attr("abs:href"));
							
							Link l2 = new Link();
							l2.setLevel(level+1);
							l2.setLink(((Node)link).attr("abs:href"));
							UrlsToCrawl.add(l2);
		
						}
					}
				}			
				
			}
		}
		
//		logger.exit();
	}
	
	public static Elements getInnerUrl(String inputUrl)
	{
		Elements hyperLinks = null;
		try {
			
			Document source;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   
			
//			logger.trace("link : " +inputUrl);
			
			//get current date time with Date()
			Date date = new Date();;
			
			source = Jsoup.connect(inputUrl).userAgent("User-Agent").timeout(10000).execute().parse();
            
			hyperLinks = source.select("a[href]");
			
//			hyperLinks = Jsoup.connect(inputUrl).userAgent("User-Agent").timeout(10000).execute().parse().select("a[href]");
			
			connect.Insert(inputUrl, source.outerHtml(),dateFormat.format(date).toString());
			
		} catch (UnsupportedMimeTypeException e) {
			
	        logger.error("Unsupported Mime type. Aborting crawling for URL: " + inputUrl);
	        e.printStackTrace();
	        
	    } catch (MalformedURLException e) {

	    	logger.error("Unsupported protocol for URL: " + inputUrl);
	        e.printStackTrace();
	        
	    } catch (HttpStatusException e) {
	    	
	    	logger.error("Error (status=" + e.getStatusCode() + ") fetching URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }catch (IllegalArgumentException e) {
	    	
	    	logger.error(" IllegalArgumentException : Must supply a valid URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }catch (IOException e) {
	    	
	    	logger.error("Timeout fetching URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }
		
		
		return hyperLinks;
		
	}
	
	public String getInputUrl() {
		return inputUrl;
	}
	public void setInputUrl(String inputUrl) {
		this.inputUrl = inputUrl;
	}
	public Elements getExtLinks() {
		return ExtLinks;
	}
	public void setExtLinks(Elements extLinks) {
		ExtLinks = extLinks;
	}
	public Document getSource() {
		return source;
	}
	public void setSource(Document source) {
		this.source = source;
	}
	
}
