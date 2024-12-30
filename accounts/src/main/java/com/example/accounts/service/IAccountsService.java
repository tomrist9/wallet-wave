package com.example.accounts.service;

import com.example.accounts.dto.CustomerDTO;
import org.springframework.stereotype.Service;


@Service
public interface IAccountsService {
    /**
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);

}
