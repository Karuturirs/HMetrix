package com.hmetrix.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.stereotype.Component;

@Component("fetchdata")
public class FetchData {
	@Autowired
	private ApplicationContext appContext;
	
	public String processUrl(String completeurl)
	{
		System.out.println("Entered processUrl in MainCluster.");
		URL url;
		String jsondata ="";
		try {
			url = new URL(completeurl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + getAuthentication("ambaricluster"));
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
		
			jsondata = sb.toString();
			boolean validJson = isJSONValid(jsondata);
			if(!validJson){
				System.out.println("Finished processUrl in MainCluster.");
				return "Not a valid json obtained from "+completeurl;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Finished processUrl in MainCluster.");
		return jsondata;
	}
	
	private String getAuthentication(String type){
		UserCredentials uc= (UserCredentials) appContext.getBean(type);
		String authString =  uc.getUsername()+ ":" + uc.getPassword();
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		return new String(authEncBytes);
	}
	
	public boolean isJSONValid(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	       	System.out.print("ERROR: Invalid Json.");
	            return false;
	       
	    }
	    return true;
	}

	
}
