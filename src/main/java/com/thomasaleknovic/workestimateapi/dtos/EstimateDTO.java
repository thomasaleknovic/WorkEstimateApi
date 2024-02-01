package com.thomasaleknovic.workestimateapi.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.thomasaleknovic.workestimateapi.models.JobDetails;

import jakarta.validation.constraints.NotNull;


public record EstimateDTO(@NotNull String estimateName, @NotNull String customerName, String cpf, @NotNull String address, String phone, BigDecimal totalPrice, List<JobDetails> jobDetails) {
    
}
