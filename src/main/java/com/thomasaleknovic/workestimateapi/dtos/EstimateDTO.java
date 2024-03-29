package com.thomasaleknovic.workestimateapi.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.thomasaleknovic.workestimateapi.models.JobDetails;

import jakarta.validation.constraints.NotNull;


public record EstimateDTO(@NotNull String estimateName, int serviceOrder, @NotNull String customerName, String cpf, String cep, @NotNull String address, String phone, String observation, String paymentMethod, BigDecimal totalPrice, List<JobDetails> jobDetails) {
    
}
