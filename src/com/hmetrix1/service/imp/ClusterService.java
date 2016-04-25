package com.hmetrix1.service.imp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmetrix1.dao.ClusterDAO;
import com.hmetrix1.dto.ClusterDTO;
import com.hmetrix1.dto.ClusterHostsDTO;
import com.hmetrix1.dto.MainCluster;
import com.hmetrix1.utils.FetchData;


public class ClusterService {

	public static void main(String[] args){
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
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
