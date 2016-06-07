package edu.mayo.test;

//http://192.168.1.91:4243
import java.io.*;
import java.net.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class DockerSearch {
	
	public DockerSearch(){}
	
	public String getSomething(String urlToRead){
		
		try{
		  StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
		} catch (Exception ex){
			System.out.println(ex);
		}
		return "Error";
	}
}
