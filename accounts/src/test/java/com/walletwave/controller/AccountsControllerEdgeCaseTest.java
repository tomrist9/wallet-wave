package com.walletwave.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletwave.dto.CustomerDTO;
import com.walletwave.exception.CustomerAlreadyExistsException;
import com.walletwave.exception.GlobalExceptionHandler;
import com.walletwave.exception.ResourceNotFoundException;
import com.walletwave.service.IAccountsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountsController.class)
@Import(GlobalExceptionHandler.class)
class AccountsControllerEdgeCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IAccountsService accountsService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        IAccountsService accountsService() {
            return Mockito.mock(IAccountsService.class);
        }
    }


    @Test
    void createAccount_whenCustomerAlreadyExists_thenReturn409() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Test User");
        dto.setEmail("test@example.com");
        dto.setMobileNumber("1234567890");

        doThrow(new CustomerAlreadyExistsException("Customer already registered"))
                .when(accountsService).createAccount(any(CustomerDTO.class));

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("CONFLICT"))
                .andExpect(jsonPath("$.errorMessage").value("Customer already registered"));
    }


    @Test
    void fetchAccount_whenNotFound_thenReturn404() throws Exception {
        String mobileNumber = "1234567890";

        when(accountsService.fetchAccount(mobileNumber))
                .thenThrow(new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        mockMvc.perform(get("/api/fetch")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("Customer not found with the given input data mobileNumber: '1234567890' "));
    }


    @Test
    void updateAccount_whenAccountNotFound_thenReturn404() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Test User");
        dto.setEmail("test@example.com");
        dto.setMobileNumber("1234567890");

        when(accountsService.updateAccount(any(CustomerDTO.class)))
                .thenThrow(new ResourceNotFoundException("Account", "accountNumber", "999"));

        mockMvc.perform(put("/api/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("Account not found with the given input data accountNumber: '999' "));
    }


    @Test
    void deleteAccount_whenCustomerNotFound_thenReturn404() throws Exception {
        String mobileNumber = "1234567890";

        when(accountsService.deleteAccount(mobileNumber))
                .thenThrow(new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        mockMvc.perform(delete("/api/delete")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("Customer not found with the given input data mobileNumber: '1234567890' "));
    }
}
