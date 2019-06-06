package com.pranavkapoorr.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@GetMapping("/greeting")
	public String myGreeting() {
		return "Hello";
	}
}
