package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
    @PostConstruct
    public void loadData(){
         theStudents=new ArrayList<>();
        theStudents.add(new Student("Jane", "Jackson"));
        theStudents.add(new Student("Mario", "Jackson"));
        theStudents.add(new Student("Jack", "Jackson"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents ;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){



        return theStudents.get(studentId);
    }

}
