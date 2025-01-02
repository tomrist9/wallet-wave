package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="CRUD REST APIs for Accounts in Wallet-wave",
        description = "CRUD REST APIs in Wallet-wave to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {


    private final IAccountsService iAccountsService;

    // Constructor injection
    public AccountsController(@Qualifier("accountServiceImpl") IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }


    @Operation(
            summary = "Create Account REST API ",
            description = "REST API to create new Customer & Account inside Wallet-wave"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer & Account details based on Mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account details fetched successfully"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        CustomerDTO customerDTO=iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
   }
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details based on Mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account details updated successfully"
    )
   @PutMapping("/update")
   public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated= iAccountsService.updateAccount(customerDTO);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
   }
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details based on Mobile number"
    )
    @ApiResponses({
            @ApiResponse(
            responseCode = "200",
            description = "Account details deleted successfully"
    ),
    @ApiResponse(
            responseCode = "500",
            description = "INTERNAL SERVER ERROR"
    )
}
    )

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }

    }

}
