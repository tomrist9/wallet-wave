package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDTO;
import com.example.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDTO mapToAccountsDTO(Accounts accounts, AccountsDTO accountsDTO){
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static Accounts mapToAccounts(Accounts accounts, AccountsDTO accountsDTO){
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }

}
