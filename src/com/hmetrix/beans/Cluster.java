package com.hmetrix.beans;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class Cluster {
	String clusterName;
	String clusterUrl;
	List<Node> nodeList;
	
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getClusterUrl() {
		return clusterUrl;
	}
	public void setClusterUrl(String clusterUrl) {
		this.clusterUrl = clusterUrl;
	}
	public List<Node> getNodeList() {
		return nodeList;
	}
	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

}
