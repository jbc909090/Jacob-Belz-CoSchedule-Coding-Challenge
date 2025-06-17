package com.example.coding.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan("com.example.coding.challenge")
@EntityScan("com.example.coding.challenge.models")
@EnableJpaRepositories("com.example.coding.challenge.repository")
public class CodingChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(CodingChallengeApplication.class, args);
		System.out.println("done");
	}


}
