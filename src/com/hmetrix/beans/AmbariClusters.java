package com.hmetrix.beans;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hmetrix.bo.ClusterListing;
import com.hmetrix.bo.RestConsumer;

@Component
public class AmbariClusters {
		
		@Autowired
		private ApplicationContext appContext;
		
		@Autowired
		RestConsumer rc ;
		@Autowired
		ClusterListing cl;
		@Autowired
		ReadProp rp ;
		
		JSONObject ambariCluster;

		public JSONObject getAmbariCluster() {
			return ambariCluster;
		}

		public void setAmbariCluster(JSONObject ambariCluster) {
			this.ambariCluster = ambariCluster;
		}

		public RestConsumer getRc() {
			return rc;
		}

		public ClusterListing getCl() {
			return cl;
		}

		

		public void setRc(RestConsumer rc) {
			this.rc = rc;
		}

		public void setCl(ClusterListing cl) {
			this.cl = cl;
		}

	
		
		public ReadProp getRp() {
			return rp;
		}

		public void setRp(ReadProp rp) {
			this.rp = rp;
		}

		public JSONObject getDataforURl(){
			String url = rp.getAmbariUrl();
			String data = rc.processUrl(url, true, "ambariUserCredntials");
			
			this.ambariCluster = cl.listClusters(data);
			return  this.ambariCluster;
		}
		
}
