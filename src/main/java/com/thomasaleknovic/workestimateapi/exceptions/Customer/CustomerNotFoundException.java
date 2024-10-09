package com.thomasaleknovic.workestimateapi.exceptions.Customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Não foi possível encontrar o cliente.");
    }
}
