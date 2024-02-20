package com.thomasaleknovic.workestimateapi.exceptions.Company;

public class CompanyNotFoundException extends  RuntimeException{
    
    public CompanyNotFoundException() {
        super("Não foi possível encontrar a empresa.");
    }
}
