package com.thomasaleknovic.workestimateapi.dtos;

import org.hibernate.mapping.List;

public record EstimateDataDTO(String customerName, Long cpf, String address, Long phone, List jobDetails) {
    
}
