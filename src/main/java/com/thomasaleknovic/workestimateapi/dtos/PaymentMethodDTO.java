package com.thomasaleknovic.workestimateapi.dtos;

import java.util.UUID;

import com.thomasaleknovic.workestimateapi.models.PaymentMethod.PaymentTitle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentMethodDTO(UUID id, @NotNull @NotBlank PaymentTitle paymentTitle, @NotNull String paymentDescription) {
   
}
