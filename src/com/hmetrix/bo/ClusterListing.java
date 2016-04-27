package com.hmetrix.bo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.hmetrix.beans.AmbariClusterHealth;
import com.hmetrix.beans.AmbariServices;


public class ClusterListing {
	@Autowired
	private  ApplicationContext appContext;

	AmbariServices ambservices;
	AmbariClusterHealth  ambclusterhealth;
	
	
	ClusterListing(AmbariServices ambservices,AmbariClusterHealth ambclusterhealth){
		this.ambservices = ambservices;
		this.ambclusterhealth = ambclusterhealth;
	
	}
	
	
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
		    if(i==0){
			    ambservices.setAmbariServices(listServices(clusterdata));
				System.out.println(listServices(clusterdata));
				ambclusterhealth.setAmbariClusterHealth(clusterHealth(clusterdata));
		    }
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
	
	public  JSONObject listServices(String data){
		
		JSONObject obj = new JSONObject(data);
		JSONObject clusterobj = new JSONObject();
		JSONArray list = new JSONArray();
		JSONArray arr = obj.getJSONArray("services");
		String clusterName = "";
		for (int i = 0; i < arr.length(); i++)
		{
		   
		    String hostName = arr.getJSONObject(i).getJSONObject("ServiceInfo").getString("service_name");
		    clusterName = arr.getJSONObject(i).getJSONObject("ServiceInfo").getString("cluster_name");
		    String href = arr.getJSONObject(i).getString("href");
		    //System.out.println("---"+href);
		    JSONObject nodelist =new JSONObject();
		    nodelist.put("service_name",hostName);
		    nodelist.put("servicehref",href);
		    list.put(nodelist);
		}
		clusterobj.put("cluster",clusterName);
		clusterobj.put("services",list);
		
		return clusterobj;
	}
	
	public  JSONObject clusterHealth(String data){
		
		JSONObject obj = new JSONObject(data);
		JSONObject clusterobj = new JSONObject();
		clusterobj.put("cluster",obj.getJSONObject("Clusters").getString("cluster_name"));
		clusterobj.put("health_report",obj.getJSONObject("Clusters").getJSONObject("health_report"));
		
		return clusterobj;
	}
	
}
