package com.walletwave.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletwave.constants.AccountsConstants;
import com.walletwave.dto.AccountsContactInfoDto;
import com.walletwave.dto.CustomerDTO;
import com.walletwave.service.IAccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


class AccountsControllerTest {

    private MockMvc mockMvc;

    private AccountsController accountsController;

    @Mock
    private IAccountsService accountsService;

    @Mock
    private Environment environment;

    @Mock
    private AccountsContactInfoDto contactInfoDto;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountsController = new AccountsController(accountsService);


        ReflectionTestUtils.setField(accountsController, "environment", environment);
        ReflectionTestUtils.setField(accountsController, "accountsContactInfoDto", contactInfoDto);
        ReflectionTestUtils.setField(accountsController, "buildVersion", "1.0");

        mockMvc = MockMvcBuilders.standaloneSetup(accountsController).build();
    }

    @Test
    void testCreateAccount() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMobileNumber("1234567890");
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john@example.com");

        doNothing().when(accountsService).createAccount(any(CustomerDTO.class));

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode", is(AccountsConstants.STATUS_201)))
                .andExpect(jsonPath("$.statusMsg", is(AccountsConstants.MESSAGE_201)));
    }

    @Test
    void testFetchAccount() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        dto.setMobileNumber("1234567890");
        dto.setName("Test User");

        when(accountsService.fetchAccount("1234567890")).thenReturn(dto);

        mockMvc.perform(get("/api/fetch").param("mobileNumber", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mobileNumber", is("1234567890")))
                .andExpect(jsonPath("$.name", is("Test User")));
    }

    @Test
    void testUpdateAccountSuccess() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMobileNumber("1234567890");
        customerDTO.setName("Updated Name");
        customerDTO.setEmail("john@example.com");

        when(accountsService.updateAccount(any())).thenReturn(true);

        mockMvc.perform(put("/api/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode", is(AccountsConstants.STATUS_200)))
                .andExpect(jsonPath("$.statusMsg", is(AccountsConstants.MESSAGE_200)));
    }

    @Test
    @DisplayName("Delete Account - Success")
    void testDeleteAccountSuccess() throws Exception {
        when(accountsService.deleteAccount("1234567890")).thenReturn(true);

        mockMvc.perform(delete("/api/delete")
                        .param("mobileNumber", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode", is(AccountsConstants.STATUS_200)))
                .andExpect(jsonPath("$.statusMsg", is(AccountsConstants.MESSAGE_200)));
    }

    @Test
    void testGetBuildInfo() throws Exception {
        var field = AccountsController.class.getDeclaredField("buildVersion");
        field.setAccessible(true);
        field.set(accountsController, "1.0.0");

        mockMvc.perform(get("/api/build-info"))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0.0"));
    }

    @Test
    void testGetJavaVersion() throws Exception {
        when(environment.getProperty("JAVA_HOME")).thenReturn("Java 17");

        mockMvc.perform(get("/api/java-version"))
                .andExpect(status().isOk())
                .andExpect(content().string("Java 17"));
    }

    @Test
    void testGetContactInfo() throws Exception {
        AccountsContactInfoDto mockDto = new AccountsContactInfoDto();
        mockDto.setMessage("Support available");
        mockDto.setContactDetails(Map.of("email", "help@walletwave.com"));
        mockDto.setOnCallSupport(List.of("123", "456"));

        var field = AccountsController.class.getDeclaredField("accountsContactInfoDto");
        field.setAccessible(true);
        field.set(accountsController, mockDto);

        mockMvc.perform(get("/api/contact-info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Support available")))
                .andExpect(jsonPath("$.contactDetails.email", is("help@walletwave.com")))
                .andExpect(jsonPath("$.onCallSupport[0]", is("123")))
                .andExpect(jsonPath("$.onCallSupport[1]", is("456")));
    }
}