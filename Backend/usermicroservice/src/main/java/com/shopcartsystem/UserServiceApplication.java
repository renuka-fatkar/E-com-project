package com.shopcartsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="User Service APIS",version="1.0",description="User Service"))
public class UserServiceApplication {

 	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
