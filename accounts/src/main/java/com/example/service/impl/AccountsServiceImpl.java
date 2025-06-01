package com.example.service.impl;



import com.example.constants.AccountsConstants;
import com.example.dto.AccountsDTO;
import com.example.dto.AccountsMsgDto;
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
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private final StreamBridge streamBridge;


    @Override
    public void createAccount(CustomerDTO customerDto) {
        Customer customer= CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number"+
                    customerDto.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        Accounts savedAccount=accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    private void sendCommunication(Accounts account, Customer customer){
        var accountsMsgDto =new AccountsMsgDto(account.getAccountNumber(), customer.getName(),
                customer.getEmail(), customer.getMobileNumber());
        log.info("Sending Communication request for the details: {}", accountsMsgDto);
        var result=streamBridge.send("sendCommunication-out-O", accountsMsgDto);
        log.info("Is the communication request successfully processed?: {}", result);

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

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated=false;
        if(accountNumber!=null){
            Accounts accounts=accountsRepository.findById(accountNumber).orElseThrow(
                    ()-> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString())
            );
            accounts.setCommunicationSw(true);
            accountsRepository.save(accounts);
            isUpdated=true;
        }
        return isUpdated;
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
