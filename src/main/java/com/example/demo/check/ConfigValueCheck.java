package com.example.demo.check;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ConfigValueCheck {
	
	@Value("${msg}")
	private String msg;
	
	@PostConstruct
	public void checkt() {
		System.out.println(msg);
	}

}
