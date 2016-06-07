package edu.mayo.docker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class Image {
	
	public Image(){}
	
	public int create(String name, String host){
		
		int ri = 0;
		
		try {

			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

			HttpPost request = new HttpPost(host+"/images/create?fromImage=" + name);
	        HttpResponse response = httpClient.execute(request);
		    System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		    ri = response.getStatusLine().getStatusCode();
		
		} catch (Exception ex){
			System.out.println(ex);
		}
		
		return ri;
	}
}
