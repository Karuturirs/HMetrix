package com.hmetrix1.dto;

import org.springframework.stereotype.Component;

import com.hmetrix1.dao.ClusterHostsDAO;

@Component
public class ClusterDTO {
	String clusterUrl;
	String clusterName;
	ClusterHostsDTO clusterHosts;
	
	public String getClusterUrl() {
		return clusterUrl;
	}
	public void setClusterUrl(String clusterUrl) {
		this.clusterUrl = clusterUrl;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	
	public ClusterHostsDTO getClusterHosts() {
		return clusterHosts;
	}
	public void setClusterHosts(ClusterHostsDTO hdto) {
		this.clusterHosts = hdto;
	}
	@Override
    public String toString() {
		return "Cluster Name:"+clusterName+" url: "+clusterUrl+ "\n hosts:"+clusterHosts.getHostsList();
	}
}
