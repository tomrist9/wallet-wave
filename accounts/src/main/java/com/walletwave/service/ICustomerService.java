package com.walletwave.service;

import com.walletwave.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
