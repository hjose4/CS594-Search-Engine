package Util;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
//import com.google.protobuf.TextFormat.ParseException;

import model.TDF;

public class ReadBigJSON {
	public static void main(String[] args) throws org.json.simple.parser.ParseException
	{
	JSONParser parser = new JSONParser();
	  Gson gson = new Gson();
	//  JSONObject jsonObject = (JSONObject) obj;
	try {

		Object obj = parser.parse(new FileReader("C:/DRIVE/big.json"));
		 BufferedReader br = new BufferedReader(  
			     new FileReader("C:/DRIVE/big.json")); 

		TDF tdf =gson.fromJson(br, TDF.class);
		 
		 String id = (String) ((HashMap) obj).get(tdf.getId());
		 int sNo= (int) ((HashMap) obj).get(tdf.getsNO()); 
         int count=(int) ((HashMap) obj).get(tdf.getCount());
		
         System.out.println("id"+id);
         System.out.println("sNo"+sNo);
         System.out.println("count"+count);
	}
	catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}catch (NullPointerException e1) {
		e1.printStackTrace();
	} catch (IOException e2) {
		e2.printStackTrace();
	}

	
}

}
