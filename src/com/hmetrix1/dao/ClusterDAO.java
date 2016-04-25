package com.hmetrix1.dao;

import com.hmetrix1.dto.ClusterDTO;
import com.hmetrix1.dto.ClusterHostsDTO;

public interface ClusterDAO {
	
	public ClusterDTO fetchCluster(String data);
	public ClusterHostsDTO fetchClusterHosts(String data);

}
