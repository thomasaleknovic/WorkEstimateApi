package com.thomasaleknovic.workestimateapi.dtos;

import com.thomasaleknovic.workestimateapi.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompanyDTO(
        String companyName,
        String cnpj,
        String phoneOne,
        String phoneTwo,
        String email,
        String website,
        String logo,
        String description,
        String socialMedia,
        String address,
        String city,
        String state,
        String zipCode,
        String country,
        UUID ownerID
) {
}