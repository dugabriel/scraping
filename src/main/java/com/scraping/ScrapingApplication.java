package com.scraping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ScrapingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrapingApplication.class, args);
	}

}
