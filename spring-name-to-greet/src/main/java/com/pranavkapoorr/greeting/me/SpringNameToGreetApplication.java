package com.pranavkapoorr.greeting.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringNameToGreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNameToGreetApplication.class, args);
	}

}
