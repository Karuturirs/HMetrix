package com.hmetrix.service.imp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmetrix.dao.ClusterDAO;
import com.hmetrix.dto.ClusterDTO;
import com.hmetrix.dto.ClusterHostsDTO;
import com.hmetrix.dto.MainCluster;
import com.hmetrix.utils.FetchData;



public class ClusterService {

	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

		 MainCluster mc = (MainCluster) context.getBean("hcluster");
         System.out.println(mc.getUrl());
         FetchData fd = (FetchData) context.getBean("fetchdata");
         String clusterjsondata = fd.processUrl(mc.getUrl());
         System.out.println(clusterjsondata);
         ClusterDAO cdao= (ClusterDAO) context.getBean("cdaoi");
         ClusterDTO dto = cdao.fetchCluster(clusterjsondata);
         String clusterHostjsondata = fd.processUrl(dto.getClusterUrl());
         ClusterHostsDTO hdto =cdao.fetchClusterHosts(clusterHostjsondata);
         dto.setClusterHosts(hdto);
         System.out.println(dto.toString());
	}

}
