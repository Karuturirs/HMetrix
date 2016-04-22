package com.hmetrix.dto;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ClusterHostsDTO {
	Map<String, String> hostsList;

	public Map<String, String> getHostsList() {
		return hostsList;
	}

	public void setHostsList(Map<String, String> hostsList) {
		this.hostsList = hostsList;
	}
	
}
