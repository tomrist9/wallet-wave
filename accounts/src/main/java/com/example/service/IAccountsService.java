package com.example.service;


import com.example.dto.CustomerDTO;
import org.springframework.stereotype.Service;



public interface IAccountsService {
    /**
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
    boolean updateCommunicationStatus(Long accountNumber);

}
