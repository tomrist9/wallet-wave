package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.hibernate.boot.model.source.internal.hbm.AttributesHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
    @Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner->{
		/*	createStudent(studentDAO);*/
			createMultipleStudents(studentDAO);
		};
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating new student object");
		Student tempStudent1=new Student("John", "Doe", "john@mail.ru");
		Student tempStudent2=new Student("Kate", "Doe", "kate@mail.ru");
		Student tempStudent3=new Student("Max", "Doe", "max@mail.ru");
		System.out.println("saving new student....");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
		System.out.println("Saved student. Generated id: "+tempStudent1.getId());
		System.out.println("Saved student. Generated id: "+tempStudent1.getId());
		System.out.println("Saved student. Generated id: "+tempStudent1.getId());

	}



	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object");
		Student tempStudent=new Student("Paul", "Doe", "paul@mail.ru");
		System.out.println("saving new student....");
		studentDAO.save(tempStudent);
		System.out.println("Saved student. Generated id: "+tempStudent.getId());
	}
}
