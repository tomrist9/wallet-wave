package com.example.controller;

import com.example.dto.CustomerDetailsDto;
import com.example.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name="REST APIs for Customers in Wallet-wave",
        description = "REST APIs in Wallet-wave to FETCH customers details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger= LoggerFactory.getLogger(CustomerController.class);
    private final ICustomerService iCustomerService;


    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on Mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer details fetched successfully"
    )

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("walletwave-correlation-id")
                                                                   String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                   String mobileNumber){
    logger.debug("fetchCustomerDetails method start");
    CustomerDetailsDto customerDetailsDto=iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
    logger.debug("fetchCustomerDetails method end");
    return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
