package com.walletwave.service.impl;

import com.walletwave.dto.AccountsDTO;
import com.walletwave.dto.CardsDto;
import com.walletwave.dto.CustomerDetailsDto;
import com.walletwave.dto.LoansDTO;
import com.walletwave.entity.Accounts;
import com.walletwave.entity.Customer;
import com.walletwave.exception.ResourceNotFoundException;
import com.walletwave.mapper.AccountsMapper;
import com.walletwave.mapper.CustomerMapper;
import com.walletwave.repository.AccountsRepository;
import com.walletwave.repository.CustomerRepository;
import com.walletwave.service.ICustomerService;
import com.walletwave.service.client.CardsFeignClient;
import com.walletwave.service.client.LoansFeignClient;
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
