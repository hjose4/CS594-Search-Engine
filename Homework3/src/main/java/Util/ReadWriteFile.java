package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

import model.Page;
import model.PageRank;
import model.TDF;

public class ReadWriteFile {
	
	
	public static void writeFile(HashMap<String, PageRank> map)
	{
		 try {
			 System.out.println("writeFile --> Started");
			 File file = new File("C:/DRIVE/rank.txt");
			 FileOutputStream f = new FileOutputStream(file);
			 ObjectOutputStream s = new ObjectOutputStream(f);
	        s.writeObject(map);
	        s.close();
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 System.out.println("writeFile --> Done");
	}
	
	
	public static void writeIndexFile(HashMap<String, ArrayList<TDF>> map)
	{
		System.out.println("writeIndexFile --> Started");
		 try {
			 File file = new File("C:/DRIVE/index.txt");
			 FileOutputStream f = new FileOutputStream(file);
			 ObjectOutputStream s = new ObjectOutputStream(f);
	        s.writeObject(map);
	        s.close();
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 System.out.println("writeIndexFile --> Done");
	}
	
	
	public static void writeUrlsFile(HashMap<String, Page> map)
	{
		System.out.println("writeUrlsFile --> Started");
		 try {
			 File file = new File("C:/DRIVE/urls.txt");
			 FileOutputStream f = new FileOutputStream(file);
			 ObjectOutputStream s = new ObjectOutputStream(f);
	        s.writeObject(map);
	        s.close();
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 System.out.println("writeUrlsFile --> Done");
	}
}
