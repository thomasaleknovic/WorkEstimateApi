package com.thomasaleknovic.workestimateapi.utils;

import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.models.Customer;

import java.util.ArrayList;
import java.util.UUID;

public class MockCustomer {

    public static String CUSTOMER_NAME = "Jurandir da silva";
    public static String CPF = "83212359643";
    public static String EMAIL = "jurandir@gmail.com";
    public static String ZIPCODE = "91120415";
    public static String ADDRESS = "Rua 1 de janeiro";
    public static String CITY = "Porto Alegre";
    public static String STATE = "Rio Grande do Sul";
    public static String COUNTRY = "Brasil";
    public static String PHONE = "(11) 933124133";
    public static UUID COMPANY_ID = UUID.fromString("64188f14-95d4-484d-b8ef-b7b1a6730afe");
    public static UUID CUSTOMER_ID = UUID.fromString("64188f14-95d4-484d-b8ef-b7b1a6730afe");

    public static CustomerDTO mockCustomerDTO () {
        return new CustomerDTO(CUSTOMER_NAME, CPF, EMAIL, PHONE, ADDRESS, CITY, STATE, ZIPCODE, COUNTRY, COMPANY_ID);

    }
    public static Customer mockCustomerEntity (){
        CustomerDTO customerDTO = new CustomerDTO(CUSTOMER_NAME, CPF, EMAIL, PHONE, ADDRESS, CITY, STATE, ZIPCODE, COUNTRY, COMPANY_ID);
        Customer customer = new Customer(COMPANY_ID, customerDTO);
        customer.setCustomerId(CUSTOMER_ID);
        return customer;
    }
}
