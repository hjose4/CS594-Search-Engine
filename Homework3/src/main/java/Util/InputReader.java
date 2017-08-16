package Util;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Raw;


public class InputReader {
	
	public static Queue<Raw> Urls = new LinkedList<Raw>();
	
	final static Logger logger = LogManager.getLogger(InputReader.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		execute("C:/Users/genus_000/Downloads/wiki-small/en");
		
	}
		
	public static Queue<Raw> execute(String path)	{	
		
		File input = new File(path);
		
		int counter = 0;
		
		Queue<File> que = new LinkedList<File>(); 
		
		File f;
		
		que.add(input);		
		
		while (que.peek()!= null )
		{
			f = que.remove();
			
			if(f.isDirectory()){
				File[] subfolders = f.listFiles();
				for(File filename : subfolders){
					que.add(filename);
				}
			}
			else
			{
				
//				logger.trace("URL : " +f);
//				FileDetails(f);
				counter++;
				Raw link = new Raw(f.getName(),f.getAbsolutePath(),counter);
				
				Urls.add(link);
			}
			
		}

		System.out.println("Done. Total "+counter+ " Records");
		
		
		return Urls;

		
		
	}
	
	public static void FileDetails(File curr_file)
	{
		String Msg;
		String type = curr_file.getName().substring(curr_file.getName().lastIndexOf('.')+1);
		
		
		
		Msg = curr_file.getName()+"|"+curr_file.getParent()+"|"+curr_file.getAbsolutePath()+"|"+type+"|"+curr_file.length()/(1024);
		
		logger.trace(" : " +Msg);
//		WriteDetails(Msg);
		
		
	}

}
