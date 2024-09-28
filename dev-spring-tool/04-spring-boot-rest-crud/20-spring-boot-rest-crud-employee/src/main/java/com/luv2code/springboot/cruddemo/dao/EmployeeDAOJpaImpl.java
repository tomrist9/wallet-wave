package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class EmployeeDAOJpaImpl implements EmployeeDAO{
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl (EntityManager theEntityManager) {
        entityManager=theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery=entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees=theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(Long theId) {
        Employee theEmployee=entityManager.find(Employee.class, theId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee=entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(Long theId) {
    Employee theEmployee=entityManager.find(Employee.class, theId);
    entityManager.remove(theEmployee);
    }
}
