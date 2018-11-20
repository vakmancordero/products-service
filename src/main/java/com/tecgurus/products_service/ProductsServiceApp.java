package com.tecgurus.products_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductsServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApp.class, args);
	}

}
