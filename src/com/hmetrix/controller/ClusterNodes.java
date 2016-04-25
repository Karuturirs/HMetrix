package com.hmetrix.controller;

import java.util.Date;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hmetrix.beans.AmbariClusters;


@RestController
public class ClusterNodes {
	
	@Autowired
	private ApplicationContext appContext;
	
	@RequestMapping(value = "/metrix", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView fetchingMetrix(){
		
		ModelAndView model = new ModelAndView("/views/metrix.jsp");
		model.addObject("message", "Cluster List.");
		model.addObject("template", "/clusternodes");
		model.addObject("nodeMetrix", "/nodemetrix");
		model.addObject("detailnodeMetrix", "/detailnodemetrix");
		model.addObject("extraMetrix", "/extrametrix");
		return model;
	}
	@RequestMapping(value = "/leftmetrix", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView leftMetrix(){
		
		ModelAndView model = new ModelAndView("/views/leftMetrix.jsp");
		model.addObject("message", "Cluster List.");
		
		return model;
	}
	
	@RequestMapping(value = "/clusternodes", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView fetchingClusterNodes(){
		
		ModelAndView model = new ModelAndView("/views/clusterNodes.jsp");
		AmbariClusters ambariCluster = (AmbariClusters) appContext.getBean("ambariclusters");
		JSONObject jo = ambariCluster.getDataforURl();
		model.addObject("message", "Cluster List");
		model.addObject("content", jo);
		return model;
	}
	@RequestMapping(value = "/nodemetrix", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView fetchingNodeMetrix(){
		
		ModelAndView model = new ModelAndView("/views/nodemetrix.jsp");
		model.addObject("message", "Node Metrix.");
		
		return model;
	}
	@RequestMapping(value = "/detailnodemetrix", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView fetchingDetailNodeMetrix(){
		
		ModelAndView model = new ModelAndView("/views/detailnodemetrix.jsp");
		model.addObject("message", "Detail Node Metrix.");
		Random rand= new Random();
		model.addObject("content", "Random number:"+rand.nextInt((1000 - 0) + 1) + 0);
		return model;
	}
	
	@RequestMapping(value = "/extrametrix", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView fetchingExtraMetrix(){
		
		ModelAndView model = new ModelAndView("/views/extrametrix.jsp");
		model.addObject("message", "Extra Metrix.");
		Date date = new Date();
		model.addObject("content", date.toString());
		return model;
	}
	@RequestMapping(value = "/footer", method = RequestMethod.GET,headers="Accept=application/json")  
	public ModelAndView footerGeneation(){
		
		ModelAndView model = new ModelAndView("/views/footer.jsp");
		//model.addObject("message", "©Copyrights. Gspann technologies PVT Ltd.");
		return model;
	}
}
