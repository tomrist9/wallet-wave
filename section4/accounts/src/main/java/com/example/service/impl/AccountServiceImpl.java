package com.example.service.impl;



import com.example.constants.AccountsConstants;
import com.example.dto.AccountsDTO;
import com.example.dto.CustomerDTO;
import com.example.entity.Accounts;
import com.example.entity.Customer;
import com.example.exception.CustomerAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AccountsMapper;
import com.example.mapper.CustomerMapper;
import com.example.repository.AccountsRepository;
import com.example.repository.CustomerRepository;
import com.example.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service

public class AccountServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    public AccountServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(CustomerDTO customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number"+
                    customerDto.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
       Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );


        Accounts accounts= accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDTO customerDTO=CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated=false;
        AccountsDTO accountsDTO=customerDTO.getAccountsDTO();
        if(accountsDTO!=null){
            Accounts accounts=accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDTO, accounts);
            accountsRepository.save(accounts);

            Long customerId=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDTO, customer);

            customerRepository.save(customer);
            isUpdated=true;

        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber= 1000000000L+new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
