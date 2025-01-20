package com.example.Banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(
	scanBasePackages = {"com.example"},
    exclude = {DataSourceAutoConfiguration.class}
)
@EnableMongoRepositories(basePackages = {"com.example.repository","com.example.controller"})
public class BankingApplication {

	public static void main(String[] args) {
		System.out.println("entered the main function");
		SpringApplication.run(BankingApplication.class, args);
	}

}
