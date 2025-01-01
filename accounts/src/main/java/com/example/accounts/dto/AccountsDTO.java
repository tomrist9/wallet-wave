package com.example.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {

    @NotEmpty(message = "AccountNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
    private Long accountNumber;


    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;

    public AccountsDTO(Long accountNumber, String accountType, String branchAddress) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public AccountsDTO() {

    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    @Override
    public String toString() {
        return "AccountsDTO{" +
                "accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                '}';
    }
}
