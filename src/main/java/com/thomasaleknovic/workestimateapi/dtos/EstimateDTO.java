package com.thomasaleknovic.workestimateapi.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.models.Customer;
import com.thomasaleknovic.workestimateapi.models.JobDetails;

import jakarta.validation.constraints.NotNull;


public record EstimateDTO(@NotNull String estimateName, int serviceOrder, String observation, String paymentMethod,
                          BigDecimal totalPrice, List<JobDetails> jobDetails, UUID customerId) {
    
}
