package com.green.universityGroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication

public class UniversityGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityGroupApplication.class, args);
	}
	
	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}


}
