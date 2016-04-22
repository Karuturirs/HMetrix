package com.hmetrix.dao.imp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmetrix.dao.ClusterDAO;
import com.hmetrix.dto.ClusterDTO;
import com.hmetrix.dto.ClusterHostsDTO;


@Service("cdaoi")
public class ClusterDAOImpl implements ClusterDAO{

	@Autowired
	ClusterDTO clusterDto;
	@Autowired
	ClusterHostsDTO clusterHostsDto;
	
	public ClusterDTO getClusterDto() {
		return clusterDto;
	}
	public void setClusterDto(ClusterDTO clusterDto) {
		this.clusterDto = clusterDto;
	}
	public ClusterHostsDTO getClusterHostsDto() {
		return clusterHostsDto;
	}
	public void setClusterHostsDto(ClusterHostsDTO clusterHostsDto) {
		this.clusterHostsDto = clusterHostsDto;
	}
	@Override
	public ClusterDTO fetchCluster(String data) {
		JSONObject obj = new JSONObject(data);
		JSONArray arr = obj.getJSONArray("items");
		for (int i = 0; i < arr.length(); i++)
		{
		    String href = arr.getJSONObject(i).getString("href");
		    String clusterName = arr.getJSONObject(i).getJSONObject("Clusters").getString("cluster_name");
		    this.clusterDto.setClusterUrl(href);
		    this.clusterDto.setClusterName(clusterName);
		}
		return this.clusterDto;
	}
	@Override
	public ClusterHostsDTO fetchClusterHosts(String data) {
		JSONObject obj = new JSONObject(data);
		JSONArray arr = obj.getJSONArray("hosts");
		Map<String, String> hostlist=new HashMap<String, String>();
		for (int i = 0; i < arr.length(); i++)
		{
		    String href = arr.getJSONObject(i).getString("href");
		    String hostName = arr.getJSONObject(i).getJSONObject("Hosts").getString("host_name");
		    hostlist.put(hostName, href);
		}
		this.clusterHostsDto.setHostsList(hostlist);
		return this.clusterHostsDto;
	}
	
	

}
