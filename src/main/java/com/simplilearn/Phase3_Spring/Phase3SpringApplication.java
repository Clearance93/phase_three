package com.simplilearn.Phase3_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Phase3SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Phase3SpringApplication.class, args);
		System.out.println("System is running on port 9091");
	}

}
