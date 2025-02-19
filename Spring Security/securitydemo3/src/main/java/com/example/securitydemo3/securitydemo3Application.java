package com.example.securitydemo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class securitydemo3Application {

	public static void main(String[] args) {
		SpringApplication.run(securitydemo3Application.class, args);
	}

	@Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}
