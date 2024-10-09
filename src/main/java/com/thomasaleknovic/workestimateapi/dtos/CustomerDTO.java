package com.thomasaleknovic.workestimateapi.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CustomerDTO(
    String name,
    String cpf,
    String email,
    String phone,
    String address,
    String city,
    String state,
    String zipCode,
    String country,
    UUID companyId) {
}
