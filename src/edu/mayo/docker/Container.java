package edu.mayo.docker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.mayo.utils.ParseJson;

public class Container {

	public Container(){}
	
	// Create a Container from an Image
	public int create(String name, String image, String host){

		try {
		    Thread.sleep(2000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}

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
				} else {
					aObject.put("PublishAllPorts", true); // publish all internal ports to random container ports
					object.put("HostConfig", aObject);
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
		    Thread.sleep(2000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
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
	
	public String getPort(){
		
		//String host = "http://192.168.1.97:4243/containers/json?all=1&before=c82af883874d&size=1";
		String host = "http://192.168.1.97:4243/containers/c82af883874d/json";
		String port = "";
		
			HttpClient client = new DefaultHttpClient();
		    HttpGet request = new HttpGet(host);
		    HttpResponse response;
		    String result = null;
		    try {
		        response = client.execute(request);         
		        HttpEntity entity = response.getEntity();

		        if (entity != null) {

		            // A Simple JSON Response Read
		            InputStream instream = entity.getContent();
		            result = convertStreamToString(instream);
		            // now you have the string representation of the HTML request
		            System.out.println("RESPONSE: " + result);
		            instream.close();

		            String jsonString = result;
		            JSONObject jsonObject = new JSONObject();

		        ParseJson pj = new ParseJson();
		        pj.parseString("["+result+"]");
		        
		        }
		        // Headers
		        org.apache.http.Header[] headers = response.getAllHeaders();
		        for (int i = 0; i < headers.length; i++) {
		            System.out.println(headers[i]);
		        }
		    } catch (ClientProtocolException e1) {
		        // TODO Auto-generated catch block
		        e1.printStackTrace();
		    } catch (IOException e1) {
		        // TODO Auto-generated catch block
		        e1.printStackTrace();
		    }
		    
		return port;
	}
	
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
