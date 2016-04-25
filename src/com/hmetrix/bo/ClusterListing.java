package com.hmetrix.bo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


public class ClusterListing {
	@Autowired
	private  ApplicationContext appContext;
	
	public  JSONObject listClusters(String data){
		
		JSONObject Mainclusterlist =new JSONObject();
		JSONArray list = new JSONArray();
		//clusterlist.put("id",1);
		JSONObject obj = new JSONObject(data);
		JSONArray arr = obj.getJSONArray("items");
		
		for (int i = 0; i < arr.length(); i++)
		{
		    String href = arr.getJSONObject(i).getString("href");
		    System.out.println("-"+href);
		    String clusterName = arr.getJSONObject(i).getJSONObject("Clusters").getString("cluster_name");
		    JSONObject clusterlist =new JSONObject();
		    clusterlist.put("name",clusterName);
		    clusterlist.put("href",href);
		    RestConsumer rc = (RestConsumer) appContext.getBean("restconsumer");
		    String clusterdata= rc.processUrl(href, true, "ambariUserCredntials");
		   JSONArray nodesarray= listNodes(clusterdata);
		   clusterlist.put("Nodes",nodesarray);;
		    list.put(clusterlist);
		    
		}
		
		Mainclusterlist.put("clusters", list);
		return Mainclusterlist;
	}
	
	
	public  JSONArray listNodes(String data){
		JSONObject obj = new JSONObject(data);
		JSONArray list = new JSONArray();
		JSONArray arr = obj.getJSONArray("hosts");
		for (int i = 0; i < arr.length(); i++)
		{
		   
		    String hostName = arr.getJSONObject(i).getJSONObject("Hosts").getString("host_name");
		    String href = arr.getJSONObject(i).getString("href");
		    System.out.println("---"+href);
		    JSONObject nodelist =new JSONObject();
		    nodelist.put("hostname",hostName);
		    nodelist.put("ambarihref",href);
		    if(hostName.startsWith("nn0")){
		    	nodelist.put("nodetype","MASTER");
		    	nodelist.put("jmxhref","http://"+hostName+":50070/jmx");
		    	nodelist.put("whatNodes","http://"+hostName+":50070/dfsnodelist.jsp?whatNodes=LIVE");
		    }else{
		    	nodelist.put("nodetype","SLAVE");
		    	nodelist.put("jmxhref","http://"+hostName+":50075/jmx");
		    	nodelist.put("nodesManagerhref","http://"+hostName+":45454/jmx");
		    }
		    list.put(nodelist);
		}
		
		
		return list;
	}
	/*public  void main(String[] args){
		//"{\"href\" : \"http://as000.cloudapp.net:8080/api/v1/clusters\",\"items\" : [{\"href\" : \"http://as000.cloudapp.net:8080/api/v1/clusters/multi-node-hdfs-yarn\",\"Clusters\" : {\"cluster_name\" : \"multi-node-hdfs-yarn\",\"version\" : \"HDP-2.2\"}},{\"href\" : \"http://as000.cloudapp.net:8080/api/v1/clusters/multi-node-hdfs-yarn1\",\"Clusters\" : {\"cluster_name\" : \"multi-node-hdfs-yarn1\",\"version\" : \"HDP-2.2\"}}]}"
		appContext = new ClassPathXmlApplicationContext("HMetrix-core-beans.xml");
		RestConsumer rc = (RestConsumer) appContext.getBean("restconsumer");
	    String clusterdata= rc.processUrl("http://as000.cloudapp.net:8080/api/v1/clusters", true, "ambariUserCredntials");
		listClusters(clusterdata);
	}*/
}
