package com.example.service.impl;

import com.example.dto.AccountsDTO;
import com.example.dto.CardsDto;
import com.example.dto.CustomerDetailsDto;
import com.example.dto.LoansDTO;
import com.example.entity.Accounts;
import com.example.entity.Customer;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AccountsMapper;
import com.example.mapper.CustomerMapper;
import com.example.repository.AccountsRepository;
import com.example.repository.CustomerRepository;
import com.example.service.ICustomerService;
import com.example.service.client.CardsFeignClient;
import com.example.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    public CustomerServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto=CustomerMapper.mapToCustomerDtoDetails(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));
        ResponseEntity<LoansDTO> loansDtoResponseEntity=loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if(null!=loansDtoResponseEntity){
            customerDetailsDto.setLoansDTO(loansDtoResponseEntity.getBody());
        }
        ResponseEntity<CardsDto> cardsDtoResponseEntity =cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        if(null!=cardsDtoResponseEntity){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}
