package com.example.Service_Exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.Service_Exam.controller")

public class ServiceExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceExamApplication.class, args);
	}

}
