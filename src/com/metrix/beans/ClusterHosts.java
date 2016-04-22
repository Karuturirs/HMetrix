package com.metrix.beans;

import java.util.Map;

public class ClusterHosts {
	/*
	 * hostsList< hostsname, hostUrl>
	 */
	Map<String, String> hostsList;

	public Map<String, String> getHostsList() {
		return hostsList;
	}

	public void setHostsList(Map<String, String> hostsList) {
		this.hostsList = hostsList;
	}
	

}
