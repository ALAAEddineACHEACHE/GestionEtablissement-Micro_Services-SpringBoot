package com.example.Service_Student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceStudentApplication.class, args);
	}

}
