package com.hmetrix.dao;

import com.hmetrix.dto.ClusterDTO;
import com.hmetrix.dto.ClusterHostsDTO;

public interface ClusterDAO {
	
	public ClusterDTO fetchCluster(String data);
	public ClusterHostsDTO fetchClusterHosts(String data);

}
