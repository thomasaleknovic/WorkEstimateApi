package com.thomasaleknovic.workestimateapi.dtos;

import com.thomasaleknovic.workestimateapi.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompanyDTO(
        String companyName,
        String cnpj,
        String cep,
        String address,
        String phoneOne,
        String phoneTwo,
        String email,
        String website,
        String logo,
        String description,
        String socialMedia,
        UUID ownerID
) {
}