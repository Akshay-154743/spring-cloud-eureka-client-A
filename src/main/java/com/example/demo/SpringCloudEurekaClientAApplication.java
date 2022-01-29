package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
@RefreshScope
@RequestMapping("/clientA")
public class SpringCloudEurekaClientAApplication {
	
    Logger logger = LoggerFactory.getLogger(SpringCloudEurekaClientAApplication.class);

	@Value("${msg}")
	private String msg;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientAApplication.class, args);
	}

	@GetMapping(value = "/welcomeText")
	public String welcomeText() {
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Thread Interupted");
		}
		return msg;
	}

}
