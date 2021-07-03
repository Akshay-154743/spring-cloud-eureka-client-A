package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
@RefreshScope
public class SpringCloudEurekaClientAApplication {

	@Value("${msg}")
	private String msg;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientAApplication.class, args);
	}

	@GetMapping(value = "/")
	public String welcomeText() {
		return msg;
	}

}
