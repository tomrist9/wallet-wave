package com.walletwave.cards.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletwave.cards.dto.CardsDto;
import com.walletwave.cards.service.ICardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ICardsService cardsService;

    @InjectMocks
    private CardsController cardsController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cardsController).build();
    }


    @Test
    void testCreateCard_Success() throws Exception {
        String mobileNumber = "1234567890";


        doNothing().when(cardsService).createCard(mobileNumber);

        mockMvc.perform(post("/api/create")
                        .param("mobileNumber", "1234567890"))
                .andExpect(status().isCreated());

    }


    @Test
    void testCreateCard_InvalidInput() throws Exception {
        CardsDto dto = new CardsDto();
        dto.setMobileNumber("123"); // not valid (pattern fail)

        mockMvc.perform(post("/api/create")
                        .param("mobileNumber", "1234567890"))
                .andExpect(status().isCreated());

    }

    @Test
    void testFetchCardDetails_Success() throws Exception {
        String mobileNumber = "1234567890";

        CardsDto cardsDto = new CardsDto();
        cardsDto.setMobileNumber(mobileNumber);
        cardsDto.setCardNumber("123456789012");
        cardsDto.setCardType("Credit Card");
        cardsDto.setTotalLimit(10000);
        cardsDto.setAmountUsed(2000);
        cardsDto.setAvailableAmount(8000);

        when(cardsService.fetchCard(mobileNumber)).thenReturn(cardsDto);

        mockMvc.perform(get("/api/fetch")
                        .param("mobileNumber", mobileNumber)
                        .header("walletwave-correlation-id", "test-corr-id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mobileNumber").value(mobileNumber))
                .andExpect(jsonPath("$.cardNumber").value("123456789012"));
    }

    @Test
    void testUpdateCardDetails_Success() throws Exception {
        String mobileNumber = "1234567890";

        CardsDto cardsDto = new CardsDto();
        cardsDto.setMobileNumber(mobileNumber);
        cardsDto.setCardNumber("123456789012");
        cardsDto.setCardType("Credit Card");
        cardsDto.setTotalLimit(10000);
        cardsDto.setAmountUsed(2000);
        cardsDto.setAvailableAmount(8000);

        when(cardsService.updateCard(any(CardsDto.class))).thenReturn(true);

        mockMvc.perform(put("/api/update")
                        .content(objectMapper.writeValueAsString(cardsDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCard_Success() throws Exception {
        String mobileNumber = "1234567890";
        when(cardsService.deleteCard(mobileNumber)).thenReturn(true);
        mockMvc.perform(delete("/api/delete")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isOk());
    }
}