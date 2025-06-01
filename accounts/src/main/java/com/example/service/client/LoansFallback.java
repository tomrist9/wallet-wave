package com.example.service.client;

import com.example.dto.LoansDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoansDTO> fetchLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
