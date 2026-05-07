package com.fitness.activityservice;

import com.mongodb.client.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class ActivityserviceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =SpringApplication.run(ActivityserviceApplication.class, args);
		// Check what Spring actually resolved
		System.out.println("=== MONGO DEBUG ===");
		System.out.println("URI: " + ctx.getEnvironment().getProperty("spring.data.mongodb.uri"));
		System.out.println("DB:  " + ctx.getEnvironment().getProperty("spring.data.mongodb.database"));

		// Check the actual MongoClient being used
		MongoClient client = ctx.getBean(MongoClient.class);
		System.out.println("Mongo connected to: " + client.listDatabaseNames().first());
	}



}
