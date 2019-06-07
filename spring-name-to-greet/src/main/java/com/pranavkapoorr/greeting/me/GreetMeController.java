package com.pranavkapoorr.greeting.me;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.*;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class GreetMeController {
	
	private static final Logger log = LoggerFactory.getLogger(GreetMeController.class);
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/greet/{name}")
	public String greetMe(@PathVariable String name){
		List<ServiceInstance> instances=discoveryClient.getInstances("greeting-producer");
		ServiceInstance serviceInstance=instances.get(0);
		String baseUrl=serviceInstance.getUri().toString();
		String greeting = consumeGreetingService(baseUrl+"/greeting");
		return greeting + " " + name;
	}
	
	private String consumeGreetingService(String serviceUrl) {
		RestTemplate greetTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		String greeting = "";
		try {
			response = greetTemplate.getForEntity(serviceUrl, String.class);
			greeting = response.hasBody()?response.getBody():"";
		}catch(Exception e) {
			 log.warn(e.getMessage());
			 greeting = "-";
		}
		return greeting;
	}
}
