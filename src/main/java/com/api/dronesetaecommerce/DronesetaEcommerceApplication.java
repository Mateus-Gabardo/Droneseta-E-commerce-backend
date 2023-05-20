package com.api.dronesetaecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
public class DronesetaEcommerceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DronesetaEcommerceApplication.class, args);
	}

}
