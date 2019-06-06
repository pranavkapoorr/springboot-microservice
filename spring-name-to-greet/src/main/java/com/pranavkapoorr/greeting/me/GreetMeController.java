package com.pranavkapoorr.greeting.me;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetMeController {
	
	private static final Logger log = LoggerFactory.getLogger(GreetMeController.class);

	@GetMapping("/greet/{name}")
	public String greetMe(@PathVariable String name){
		String greeting = consumeGreetingService("http://localhost:40001/greeting");
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
