package com.metrix.beans;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.metrix.utils.Consumer;

@Component
public class Clusters {
	@Autowired
	private ApplicationContext appContext;
	
	String url;
	Map<String, Cluster> clusters;
	
	Clusters(String url){
		this.url=url;
		Consumer consumer = (Consumer) appContext.getBean("consumer");
		String jsondata = consumer.processUrl(url);
		
	}
	
	public String getUrl() {
		return url;
	}
	
	public Map<String, Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(Map<String, Cluster> clusters) {
		this.clusters = clusters;
	}
	
	public JSONObject processClusterJson(String data){
		
		JSONObject obj = new JSONObject(data);
		JSONArray arr = obj.getJSONArray("items");
		for (int i = 0; i < arr.length(); i++)
		{
		    String href = arr.getJSONObject(i).getString("href");
		    String clusterName = arr.getJSONObject(i).getJSONObject("Clusters").getString("cluster_name");
		    Cluster cluster= new Cluster(clusterName,href,new ClusterHosts());
		    clusters.put(clusterName, cluster);
		}
		return null;
	}

}
