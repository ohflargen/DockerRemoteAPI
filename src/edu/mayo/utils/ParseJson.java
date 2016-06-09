package edu.mayo.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJson {
	
	public ParseJson(){}
	
	public String parseString(String jsonString){
	       JSONParser parser = new JSONParser();

	        try {     
	        	
	            JSONArray a = (JSONArray) parser.parse(jsonString);
	            
	            for (Object o : a)
	            {
	              JSONObject container = (JSONObject) o;

	              String jName = (String) container.get("Id");
	              System.out.println(jName);
	              System.out.println(container.containsKey("Id"));
	              
	              if(jName.equalsIgnoreCase("State")){
	            	  JSONObject subcontainer = (JSONObject) o;
	            	  System.out.println(subcontainer.containsKey("Running"));
	              }
	              
	              
	              /*
	              JSONArray cars = (JSONArray) person.get("cars");
	              
	              for (Object c : cars)
	              {
	                System.out.println(c+"");
	              }
	            */  
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
		return "thing";
	}

}
