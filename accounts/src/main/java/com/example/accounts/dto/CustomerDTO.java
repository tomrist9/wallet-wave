package com.example.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Singular;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name can not be empty")
    @Size(min=5, max=30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "The email address should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private AccountsDTO accountsDTO;


    public CustomerDTO(String name, String email, String mobileNumber, AccountsDTO accountsDTO) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountsDTO = accountsDTO;
    }

    public CustomerDTO(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public CustomerDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public AccountsDTO getAccountsDTO() {
        return accountsDTO;
    }
    public void setAccountsDTO(AccountsDTO accountsDTO) {
        this.accountsDTO = accountsDTO;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", accountsDTO=" + accountsDTO +
                '}';
    }
}