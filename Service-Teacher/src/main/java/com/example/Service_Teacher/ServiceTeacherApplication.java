package com.example.Service_Teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTeacherApplication.class, args);
	}

}
