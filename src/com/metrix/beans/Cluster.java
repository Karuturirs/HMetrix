package com.metrix.beans;

public class Cluster {
		String host;
		String port;
		String url;
		
		Cluster(String host,String port){
			this.host=host;
			this.port=port;
			this.url = "http://"+host+":"+port;
		}
		public String getHost() {
			return host;
		}
		
		public String getPort() {
			return port;
		}
		public String getUrl() {
			return url;
		}
		
}
