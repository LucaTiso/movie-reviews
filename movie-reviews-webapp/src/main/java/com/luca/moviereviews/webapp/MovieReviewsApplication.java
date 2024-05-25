package com.luca.moviereviews.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = { "com.luca.moviereviews.jpa", "com.luca.moviereviews.core",
		"com.luca.moviereviews.webapp" })
public class MovieReviewsApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieReviewsApplication.class, args);

		System.out.println("miao");
	}

}
