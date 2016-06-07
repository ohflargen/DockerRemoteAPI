package edu.mayo.docker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Container {

	public Container(){}
	
	// Create a Container from an Image
	public int create(String name, String image, String host){
			
			int ri = 0;
			
			JSONObject object = new JSONObject();
			JSONObject aObject = new JSONObject();
			JSONObject bObject = new JSONObject();
			JSONObject cObject = new JSONObject();
			
			JSONArray list = new JSONArray();

			try {
			//Image to use
			object.put("Image", image);
			
				if (image.equalsIgnoreCase("tomcat")){
					//HostConfig
					list.add("/home/mkwalter/tomcat/webapps:/usr/local/tomcat/webapps"); // host path: container path
					aObject.put("Binds",list); // add list to Binds
					aObject.put("PublishAllPorts", true); // publish all internal ports to random container ports
					object.put("HostConfig", aObject);
					
					
					// Mounts
					bObject.put("RW", true);
					bObject.put("Destination", "/usr/local/tomcat/webapps");
					bObject.put("Source", "/home/mkwalter/tomcat/webapps");
					bObject.put("Mode", "");
					bObject.put("Propagation", "rprivate");
					cObject.put("Mounts", bObject);
					
					// Combine all configuration data
					object.put("Config", cObject);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			
			try {

				HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

				HttpPost request = new HttpPost(host+"/containers/create?name="+name);
			     StringEntity params = new StringEntity(object.toString());
			     request.addHeader("content-type", "application/json");
			     request.setEntity(params);
			     HttpResponse response = httpClient.execute(request);
			        
			     System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			     ri = response.getStatusLine().getStatusCode();
			
			} catch (Exception ex){
				System.out.println(ex);
			}
			
			return ri;
			
	}
	
	// Start a Container that has already been created
	public int start(String name, String host){
		int ri = 0;
		
		try {

			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

			 HttpPost request = new HttpPost(host+"/containers/"+name+"/start");
		     request.addHeader("content-type", "application/json");
		        HttpResponse response = httpClient.execute(request);
		        
		        System.out.println("Response Code : " 
		                + response.getStatusLine().getStatusCode());
		        ri = response.getStatusLine().getStatusCode();
		
		} catch (Exception ex){
			System.out.println(ex);
		}
		return ri;
	}

	// Stop a running Container
	public int stop(String name, String host){
		int ri = 0;
		
		try {

			HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

			 HttpPost request = new HttpPost(host+"/containers/"+name+"/stop?t=5"); //t=5 number of seconds to wait before killing the container
		     request.addHeader("content-type", "application/json");
		        HttpResponse response = httpClient.execute(request);
		        
		        System.out.println("Response Code : " 
		                + response.getStatusLine().getStatusCode());
		        ri = response.getStatusLine().getStatusCode();
		
		} catch (Exception ex){
			System.out.println(ex);
		}
		return ri;
	}
}
