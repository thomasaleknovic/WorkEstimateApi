package com.thomasaleknovic.workestimateapi.dtos;

import java.sql.Date;

import com.thomasaleknovic.workestimateapi.models.EstimateData;

public record EstimateDTO(String estimateName, Date createdAt, EstimateData estimateData) {
    
}
