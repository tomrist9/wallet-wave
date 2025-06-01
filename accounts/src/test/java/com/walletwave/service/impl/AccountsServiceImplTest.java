package com.walletwave.service.impl;

import com.walletwave.dto.CustomerDTO;
import com.walletwave.entity.Accounts;
import com.walletwave.entity.Customer;
import com.walletwave.exception.CustomerAlreadyExistsException;
import com.walletwave.repository.AccountsRepository;
import com.walletwave.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

class AccountsServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private AccountsServiceImpl accountsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCustomerNotExists_thenCreateAccountAndSendMessage() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Alice");
        customerDTO.setMobileNumber("12345");
        customerDTO.setEmail("alice@example.com");

        Customer mappedCustomer = new Customer();
        mappedCustomer.setCustomerId(1L);
        mappedCustomer.setName("Alice");
        mappedCustomer.setMobileNumber("12345");
        mappedCustomer.setEmail("alice@example.com");

        Accounts savedAccount = new Accounts();
        savedAccount.setAccountNumber(1234567890L);
        savedAccount.setCustomerId(1L);

        when(customerRepository.findByMobileNumber("12345")).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(mappedCustomer);
        when(accountsRepository.save(any(Accounts.class))).thenReturn(savedAccount);
        when(streamBridge.send(eq("sendCommunication-out-O"), any())).thenReturn(true);


        accountsService.createAccount(customerDTO);


        verify(customerRepository).save(any(Customer.class));
        verify(accountsRepository).save(any(Accounts.class));
        verify(streamBridge).send(eq("sendCommunication-out-O"), any());
    }

    @Test
    void createAccount_shouldThrowExceptionIfCustomerExists() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMobileNumber("12345");

        when(customerRepository.findByMobileNumber("12345"))
                .thenReturn(Optional.of(new Customer()));

        assertThrows(CustomerAlreadyExistsException.class, () ->
                accountsService.createAccount(customerDTO));

        verify(customerRepository, never()).save(any());
        verify(accountsRepository, never()).save(any());
        verify(streamBridge, never()).send(any(), any());
    }
}