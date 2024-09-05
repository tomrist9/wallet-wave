package com.example.demo;

import com.gfg.demo.domain.College;
import com.gfg.demo.domain.CollegeConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context
				= new AnnotationConfigApplicationContext(
				CollegeConfig.class);

		// Getting the bean
		College college
				= context.getBean("collegeBean", College.class);

		// Invoking the method
		// inside main() method
		college.test();
	}
}
