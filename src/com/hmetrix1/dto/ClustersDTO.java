package com.hmetrix1.dto;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ClustersDTO {
	Map<String, ClusterDTO> clustersList;

	public Map<String, ClusterDTO> getClustersList() {
		return clustersList;
	}

	public void setClustersList(Map<String, ClusterDTO> clustersList) {
		this.clustersList = clustersList;
	}
}
