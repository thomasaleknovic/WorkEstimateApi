package com.thomasaleknovic.workestimateapi.dtos;

import java.util.List;

public record EstimateDTO(String estimateName, String customerName, Long cpf, String address, Long phone, List<String> jobDetails) {
    
}
