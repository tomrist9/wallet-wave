package com.walletwave.loans.mapper;


import com.walletwave.loans.dto.LoansDTO;
import com.walletwave.loans.entity.Loans;

public class LoansMapper {

    public static LoansDTO mapToLoansDto(Loans loans, LoansDTO loansDto) {
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDTO loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }

}
