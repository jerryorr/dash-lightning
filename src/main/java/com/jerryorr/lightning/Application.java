package com.jerryorr.lightning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Basic Spring Boot annotation-based configuration
 * 
 * @author jerryorr
 */
@EnableAsync // this is necessary if we want to use @Async annotations
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
