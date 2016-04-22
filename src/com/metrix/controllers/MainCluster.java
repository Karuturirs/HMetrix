package com.metrix.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metrix.beans.Clusters;
import com.metrix.utils.Consumer;

@Controller
public class MainCluster {
	
	@Autowired
	private ApplicationContext appContext;
	
	@RequestMapping(value = "/leftnav", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		System.out.println("Hello world2");
		//String url = (Clusters)appContext.getBean("ambariclusters").get
		
		model.put("title", "header");
		model.put("message","ravi sankar");
		
		return "hello";
	}
	
}
