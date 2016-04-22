package com.metrix.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.authentication.UserCredentials;

public class Consumer implements ApplicationContextAware {
	@Autowired
	private ApplicationContext appContext;
	
	public String processUrl(String completeurl)
	{
		System.out.println("Entered processUrl in MainCluster.");
		URL url;
		try {
			url = new URL(completeurl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + getAuthentication("ambaricluster"));
			System.out.println("Finoished processUrl in MainCluster.");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	private String getAuthentication(String type){
		UserCredentials uc= (UserCredentials) appContext.getBean(type);
		String authString =  uc.getUsername()+ ":" + uc.getPassword();
		System.out.println("auth string: " + authString);
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	this.appContext = applicationContext;
		
	}
}
