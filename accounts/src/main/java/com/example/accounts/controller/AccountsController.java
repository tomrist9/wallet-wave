package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.IAccountsService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {


    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount( @RequestBody CustomerDTO customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
   public ResponseEntity<CustomerDTO> fetchAccountDetails()

}
