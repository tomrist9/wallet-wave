package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="CustomerDetails",
        description = "Schema to hold Customer, Accounts, Loans and Cards information"
)
public class CustomerDetailsDto {
    @Schema(
            description="Name of the customer", example = "Wallet-wave"
    )
    @NotEmpty(message = "Name can not be empty")
    @Size(min=5, max=30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description="Email address of the customer", example = "customer@walletwave@gmail.com"
    )
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "The email address should be valid")
    private String email;

    @Schema(
            description="Mobile number of the customer", example = "994556754455"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description="Account address of the customer"
    )
    private AccountsDTO accountsDTO;

    @Schema(
            description="Loans details of the customer"
    )
    private LoansDTO loansDTO;

    @Schema(
            description="Cards details of the customer"
    )
    private CardsDto cardsDto;


    public CustomerDetailsDto(String name, String email, String mobileNumber, AccountsDTO accountsDTO) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountsDTO = accountsDTO;
    }

    public CustomerDetailsDto(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public CustomerDetailsDto() {

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

    public LoansDTO getLoansDTO() {
        return loansDTO;
    }

    public void setLoansDTO(LoansDTO loansDTO) {
        this.loansDTO = loansDTO;
    }

    public CardsDto getCardsDto() {
        return cardsDto;
    }

    public void setCardsDto(CardsDto cardsDto) {
        this.cardsDto = cardsDto;
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
