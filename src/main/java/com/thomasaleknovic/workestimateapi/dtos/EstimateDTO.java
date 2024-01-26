package com.thomasaleknovic.workestimateapi.dtos;

import java.util.List;

import com.thomasaleknovic.workestimateapi.models.JobDetails;

import jakarta.validation.constraints.NotNull;


public record EstimateDTO(@NotNull String estimateName, @NotNull String customerName, Long cpf, @NotNull String address, Long phone, List<JobDetails> jobDetails) {
    
}
