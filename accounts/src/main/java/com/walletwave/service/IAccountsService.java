package com.walletwave.service;


import com.walletwave.dto.CustomerDTO;


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
