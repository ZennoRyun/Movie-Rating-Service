package com.example.movierating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MovieratingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieratingApplication.class, args);
	}

}
