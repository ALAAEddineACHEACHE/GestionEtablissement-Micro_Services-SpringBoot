package com.example.Service_Teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTeacherApplication.class, args);
	}

}
