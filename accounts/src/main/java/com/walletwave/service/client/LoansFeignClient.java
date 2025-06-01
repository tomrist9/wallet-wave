package com.walletwave.service.client;

import com.walletwave.dto.LoansDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoansDTO> fetchLoanDetails(
            @RequestHeader("walletwave-correlation-id") String correlationId,
            @RequestParam("mobileNumber") String mobileNumber
    );
}
