package com.example.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
	
	
	@Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        
		System.out.println("CPU Core: " + Runtime.getRuntime().availableProcessors());
        System.out.println("CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism());
        
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(1);
        executor.setThreadNamePrefix("CarThread-");
        executor.initialize();
        return executor;
    }

	@Value("${msg}")
	private String msg;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientAApplication.class, args);
	}

	@GetMapping(value = "/welcomeText")
	public String welcomeText() throws InterruptedException, ExecutionException {
		logger.info("init --------  ::::: "+Thread.currentThread().getName());
		
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				logger.info("--------  ::::: "+Thread.currentThread().getName());
				

		}, taskExecutor());
		
		runAsync.get();
		
		return msg;
	}
	
	@GetMapping(value = "/welcomeText2")
	public String welcomeText2() throws InterruptedException, ExecutionException {
		logger.info("init2 --------  ::::: "+Thread.currentThread().getName());
		
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("--------  ::::: 2"+Thread.currentThread().getName());
			
			
		});
		
		runAsync.get();
		
		return msg;
	}

}
