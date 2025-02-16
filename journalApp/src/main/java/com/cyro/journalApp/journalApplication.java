package com.cyro.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableTransactionManagement
public class journalApplication {
	public static void main(String[] args) {
		SpringApplication.run(journalApplication.class, args);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}
