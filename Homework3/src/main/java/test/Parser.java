package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
try {
		
			
			
//			logger.trace("Extracting : "+path);
						
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date date = new Date();
//			System.out.println(file);
	
//			System.out.println("file :"+file+" md5 :"+MD5(file));
			
//			String filename = URLEncoder.encode(inputUrl, "UTF-8");
//			Path currentRelativePath = Paths.get("");
//			String s = currentRelativePath.toAbsolutePath().toString();
//						
//			WriteFile(s+"/data/"+filename+".html", ViewSource);
//			
//						
//			Document source = Jsoup.parse(ViewSource);
			
			File f = new File("C:/Users/genus_000/Downloads/munged/munged/00d8f73d738f0982a6966bf6acad9323.html");
//			File f = new File("C:/Users/genus_000/Downloads/wiki-small/en/articles/2E/i/c/.ics.html");

			Document source = Jsoup.parse(f, "UTF-8");
			
			try
			{
			String title = "";
			if(source.title()!=null)
				title = source.title();
			
			System.out.println("title"+title);
			
			
			String Bodytext = "";
			if(source.body().text()!=null)
				Bodytext = source.body().text();
			
			System.out.println("Bodytext"+Bodytext);
			
			
			}catch(NullPointerException e){
				System.out.println("No text or body");
			}
			
			
			
			
			String description = "";
			try
			{
			if(source.select("meta[name=description]").get(0)!=null)
				description = source.select("meta[name=description]").get(0).attr("content");
			
			}catch(IndexOutOfBoundsException e){
//				logger.error("description not found");
			}
			
			System.out.println("description"+description);
			
			String keywords = "";
			try
			{
			if(source.select("meta[name=keywords]").first()!=null)
				keywords = source.select("meta[name=keywords]").first().attr("content");
			}catch(NullPointerException e){
			}
		
			System.out.println("keywords"+keywords);
			
			
			Elements media = source.select("[src]");
			Elements imports = source.select("link[href]");
		    Elements links = source.select("a[href]");// source.select("a")
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
		    			if (!src.attr("src").equals(""))
		    				Img.add(src.attr("src"));
		            }
		            else
		            {
		            	if (!src.attr("src").equals(""))
		            		Script.add(src.attr("src")); //src.attr("abs:src")
		            }
	        	}   	
	           
	        }
		    
		   
		    
		    System.out.println("Script"+Script);
		    System.out.println("Img"+Img);
		    
		    
		    for (Element link : imports) {
		    	
		    	if (link!= null && !link.equals(""))
	        	{
		    		if (!link.attr("href").equals(""))//.attr("abs:href")
		    			Imports.add(link.attr("href"));
	        	}
	            
	        }
		    System.out.println("Imports"+Imports);
		   
		    
		    for (Element link : links) {
	        	if (link!= null && !link.equals(""))
	        	{
	        	
	        		if (!link.attr("href").equals(""))
//	        			System.out.println(link.attr("abs:href"));
//	        		Links.add(MD5(link.attr("abs:href")));
	        		Links.add(link.attr("href"));  //.attr("href")
	        	
	        	}
	        }
		    
		    System.out.println("HyperLink"+Links);
		    System.out.println("HyperLink size"+Links.size());
		    
		   
//		     
//		    
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
		    
		    System.out.println("Metadata"+Metadata);
//		 
//		
		
//		    index.UrlsExtracted.add(p);
	
		    
		
		
		
//		String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
//		Document doc = Jsoup.parse(html);
		
		Elements lin = source.select("a");
		
//		Element link = doc.select("a").first();

		String text = source.body().text(); // "An example link"
		System.out.println("text--->"+text);
		
		for (Element link : lin)
		{
		
		String linkHref = link.attr("href"); // "http://example.com/"
		System.out.println("linkHref--->"+linkHref);
		String linkText = link.text(); // "example""
		System.out.println("linkText--->"+linkText);
		String linkOuterH = link.outerHtml(); 
		System.out.println("linkOuterH--->"+linkOuterH);
		    // "<a href="http://example.com"><b>example</b></a>"
		String linkInnerH = link.html(); // "<b>example</b>"
		System.out.println("linkInnerH--->"+linkInnerH);
		
		}
		

} catch (IllegalArgumentException e) {
	
//	logger.error(" IllegalArgumentException : Must supply a valid URL: " + f);
    e.printStackTrace();
    
}catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}


