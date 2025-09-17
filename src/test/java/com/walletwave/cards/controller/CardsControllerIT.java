package com.walletwave.cards.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletwave.cards.dto.CardsDto;
import com.walletwave.cards.repository.CardsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CardsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String mobileNumber = "1234567890";

    @BeforeEach
    void setUp() {
        cardsRepository.deleteAll();
    }

    @Test
    void testCreateCard_Success() throws Exception {
        mockMvc.perform(post("/api/create")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.statusMsg").value("Card created successfully"));
    }

    @Test
    void testFetchCardDetails_Success() throws Exception {

        mockMvc.perform(post("/api/create")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/fetch")
                        .param("mobileNumber", mobileNumber)
                        .header("walletwave-correlation-id", "corr-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mobileNumber").value(mobileNumber));
    }

    @Test
    void testUpdateCard_Success() throws Exception {

        mockMvc.perform(post("/api/create")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isCreated());

        CardsDto dto = new CardsDto();
        dto.setMobileNumber(mobileNumber);
        dto.setCardNumber(cardsRepository.findByMobileNumber(mobileNumber).get().getCardNumber());
        dto.setCardType("Credit Card");
        dto.setTotalLimit(20000);
        dto.setAmountUsed(5000);
        dto.setAvailableAmount(15000);

        mockMvc.perform(put("/api/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.statusMsg").value("Request processed successfully"));
    }

    @Test
    void testDeleteCard_Success() throws Exception {

        mockMvc.perform(post("/api/create")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/delete")
                        .param("mobileNumber", mobileNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.statusMsg").value("Request processed successfully"));
    }
}