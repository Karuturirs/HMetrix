package com.metrix.practice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.metrix.beans.Cluster;
import com.metrix.utils.Consumer;


@Controller
public class HelloWorld {
		
		@Autowired
		private ApplicationContext appContext;
		
		@RequestMapping(method = RequestMethod.GET)
		public String welcome(ModelMap model)
		{
			System.out.println("Hello world welcome");
			model.addAttribute("message", "Welcome you ravi");
			model.addAttribute("message", "Welcome you ravi");
			return "hello";
		}
		
		@RequestMapping(value="/other", method = RequestMethod.GET)
		public String into(ModelMap model)
		{
			System.out.println("Hello world welcome");
			model.addAttribute("message", "Welcome you ravi");
			model.addAttribute("message", "Welcome you ravi");
			return "hello";
		}
		
		@RequestMapping(value="/1", method = RequestMethod.GET)
		public ModelAndView intoFirst()
		{
			System.out.println("Hello world1");
			ModelAndView model= new ModelAndView("Start");
			model.addObject("message", "Welcome you Starting Page!");
			Cluster c=(Cluster) appContext.getBean("cluster");
			//System.out.println(c.getUrl());
			return model;
		}
		@RequestMapping(value = "/2", method = RequestMethod.GET)
		public String index(Map<String, Object> model) {

			System.out.println("Hello world2");
			Consumer consumer = (Consumer) appContext.getBean("consumer");
			consumer.processUrl("http://as000.cloudapp.net:8080/api/v1/clusters");
			model.put("title", "header");
			model.put("message","ravi sankar");
			
			return "hello";
		}

		@RequestMapping(value = "/hello/x", method = RequestMethod.GET)
		public ModelAndView hello( String name) {

			System.out.println("Hello world hell");

			ModelAndView model = new ModelAndView();
			model.setViewName("hello");
			//model.addAttribute(,);
			model.addObject("title", "header");
			model.addObject("message", "karuturi");
			
			return model;

		}
	
}
