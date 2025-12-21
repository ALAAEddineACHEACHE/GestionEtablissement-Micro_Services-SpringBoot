package com.example.Service_Schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.example.Service_Schedule.repo")
@EntityScan(basePackages = "com.example.Service_Schedule")
public class ServiceScheduleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceScheduleApplication.class, args);
	}
}
