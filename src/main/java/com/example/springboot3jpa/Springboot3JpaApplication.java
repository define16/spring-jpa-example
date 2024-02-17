package com.example.springboot3jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Springboot3JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot3JpaApplication.class, args);
	}

}
