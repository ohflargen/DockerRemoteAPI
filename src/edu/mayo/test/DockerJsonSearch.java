package edu.mayo.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*

import org.json.JSONObject;

public class DockerJsonSearch {
	
	public DockerJsonSearch(){}
	
	public String getThingy(String urlToRead){
	
	      StringBuilder jsonString = new StringBuilder();

	      try{
		  StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String readAPIResponse = " ";
	      while ((readAPIResponse = rd.readLine()) != null) {
	         result.append(jsonString);
	      }
	      rd.close();
			JSONObject jsonObj = new JSONObject(jsonString.toString());
			System.out.println(jsonString.toString());
			System.out.println("---------------------------");
			System.out.println(jsonObj);
	      return result.toString();
		} catch (Exception ex){
			System.out.println(ex);
		}
		return "Error";
	}
}
*/
