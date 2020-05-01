package com.ah.algov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Spring boot application that serve as a naive code challenge platform, the
 * whole purpose is to consume AWS palindromer service on AWS side and sending
 * back results to the service in 3 seconds or less.
 *
 */
@SpringBootApplication
public class AlgoVApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(AlgoVApplication.class, args);
	}
}
