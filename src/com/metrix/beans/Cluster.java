package com.metrix.beans;


public class Cluster {
		
		String clusterUrl;
		String clusterName;
		ClusterHosts clusterHosts;
		
		public Cluster(String clusterUrl, String clusterName,
				ClusterHosts clusterHosts) {
			//super();
			this.clusterUrl = clusterUrl;
			this.clusterName = clusterName;
			this.clusterHosts = clusterHosts;
		}
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
		public ClusterHosts getClusterHosts() {
			return clusterHosts;
		}
		public void setClusterHosts(ClusterHosts clusterHosts) {
			this.clusterHosts = clusterHosts;
		}
		
		
		
		
}
