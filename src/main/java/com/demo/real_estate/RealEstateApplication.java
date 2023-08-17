package com.demo.real_estate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.demo"})
public class RealEstateApplication {


	public static void main(String[] args) {
		SpringApplication app =
                new SpringApplication(RealEstateApplication.class);
		
		app.run(args);
	}
 
}
