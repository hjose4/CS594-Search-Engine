package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import model.Page;
import model.PageRank;
import model.TDF;

public class ReadWriteFile {
	
	
	public static void writeFile(HashMap<String, PageRank> map)
	{
		 try {
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
	
	public static HashMap<String, ArrayList<TDF>> readIndexFile()
	{
		System.out.println("readIndexFile --> Started");
		
		
		

        HashMap<String, ArrayList<TDF>> mapInFile = new HashMap<String, ArrayList<TDF>>();
		
		 //read from file 
	    try{
	        File toRead=new File("C:/DRIVE/index.txt");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);

			
				mapInFile = (HashMap<String, ArrayList<TDF>>)ois.readObject();
			

	        ois.close();
	        fis.close();
	        //print All data in MAP
//	        for(Entry<String, ArrayList<TDF>> m :mapInFile.entrySet()){
//	            System.out.println(m.getKey()+" : "+m.getValue());
//	        }
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 System.out.println("readIndexFile --> Done");
		 
		 return mapInFile;
	}
	
	public static HashMap<String, PageRank> readRankFile()
	{
		System.out.println("readRankFile --> Started");
		
		
		

		HashMap<String, PageRank> mapInFile = new HashMap<String, PageRank>();
		
		 //read from file 
	    try{
	        File toRead=new File("C:/DRIVE/rank.txt");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);

			
				mapInFile = (HashMap<String, PageRank>)ois.readObject();
			

	        ois.close();
	        fis.close();
	        //print All data in MAP
//	        for(Entry<String, ArrayList<TDF>> m :mapInFile.entrySet()){
//	            System.out.println(m.getKey()+" : "+m.getValue());
//	        }
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 System.out.println("readRankFile --> Done");
		 
		 return mapInFile;
	}
	
	public static HashMap<String, Page> readUrlsFile()
	{
		System.out.println("readUrlsFile --> Started");
		
		
		

        HashMap<String, Page> mapInFile = new HashMap<String, Page>();
		
		 //read from file 
	    try{
	        File toRead=new File("C:/DRIVE/urls.txt");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);

			
				mapInFile = (HashMap<String, Page>)ois.readObject();
			

	        ois.close();
	        fis.close();
	        //print All data in MAP
//	        for(Entry<String, ArrayList<TDF>> m :mapInFile.entrySet()){
//	            System.out.println(m.getKey()+" : "+m.getValue());
//	        }
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 System.out.println("readUrlsFile --> Done");
		 
		 return mapInFile;
	}
}
