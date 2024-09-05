package com.example.user_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UserManagementApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "9093"));
		app.run(args);
	}

}
