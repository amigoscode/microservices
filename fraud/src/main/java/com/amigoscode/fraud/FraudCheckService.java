package com.amigoscode.fraud;

public interface FraudCheckService {
    boolean isFraudulentCustomer(Integer customerId);
}
