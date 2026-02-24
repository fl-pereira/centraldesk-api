package com.felipe.centraldesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CentralDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralDeskApplication.class, args);
	}

}
