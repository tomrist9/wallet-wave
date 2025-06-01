package com.walletwave.controller;

import com.walletwave.dto.AccountsDTO;
import com.walletwave.dto.CustomerDTO;
import com.walletwave.service.IAccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IAccountsService accountsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFetchAccountDetails() throws Exception {
        String mobileNumber = "1234567890";

        AccountsDTO accountsDTO = new AccountsDTO(1234567890L, "Savings", "Main Branch");
        CustomerDTO customerDTO = new CustomerDTO("John Doe", "john@example.com", mobileNumber, accountsDTO);

        Mockito.when(accountsService.fetchAccount(eq(mobileNumber))).thenReturn(customerDTO);

        mockMvc.perform(get("/api/fetch")
                        .param("mobileNumber", mobileNumber)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.mobileNumber").value(mobileNumber))
                .andExpect(jsonPath("$.accountsDTO.accountType").value("Savings"));
    }
}
