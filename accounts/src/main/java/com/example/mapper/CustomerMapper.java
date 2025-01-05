package com.example.mapper;


import com.example.dto.CustomerDTO;
import com.example.entity.Customer;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO){
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;

    }
    public static Customer mapToCustomer( CustomerDTO customerDTO, Customer customer){
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;

    }
}
