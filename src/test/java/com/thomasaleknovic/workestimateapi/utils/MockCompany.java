package com.thomasaleknovic.workestimateapi.utils;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;
import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import com.thomasaleknovic.workestimateapi.models.Company;
import com.thomasaleknovic.workestimateapi.models.Customer;

import java.util.UUID;

public class MockCompany {

    public static String COMPANY_NAME = "Tech Innovators Ltda";
    public static String CNPJ = "12.345.678/0001-99";
    public static String ADDRESS = "Avenida Inovação, 1500";
    public static String CITY = "São Paulo";
    public static String STATE = "São Paulo";
    public static String ZIPCODE = "01310930";
    public static String COUNTRY = "Brasil";
    public static String PHONE_ONE = "(11) 98888-1234";
    public static String PHONE_TWO = "(11) 97777-5678";
    public static String EMAIL = "contato@techinnovators.com.br";
    public static String WEBSITE = "https://www.techinnovators.com.br";
    public static String LOGO = "https://www.techinnovators.com.br/logo.png";
    public static String DESCRIPTION = "Empresa líder em soluções tecnológicas inovadoras.";
    public static String SOCIAL_MEDIA = "https://www.linkedin.com/company/techinnovators";
    public static UUID OWNER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    public static UUID COMPANY_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");


    public static CompanyDTO mockCompanyDTO () {
        return new CompanyDTO(
                COMPANY_NAME,
                CNPJ,
                PHONE_ONE,
                PHONE_TWO,
                EMAIL,
                WEBSITE,
                LOGO,
                DESCRIPTION,
                SOCIAL_MEDIA,
                ADDRESS,
                CITY,
                STATE,
                ZIPCODE,
                COUNTRY,
                OWNER_ID
        );

    }
    public static Company mockCompanyEntity (){
        CompanyDTO companyDTO = new CompanyDTO(
                COMPANY_NAME,
                CNPJ,
                PHONE_ONE,
                PHONE_TWO,
                EMAIL,
                WEBSITE,
                LOGO,
                DESCRIPTION,
                SOCIAL_MEDIA,
                ADDRESS,
                CITY,
                STATE,
                ZIPCODE,
                COUNTRY,
                OWNER_ID
        );
        Company company = new Company(companyDTO);
        company.setCompanyId(COMPANY_ID);
        return company;
    }
}
