package com.thomasaleknovic.workestimateapi.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;


public record JobDetailsDTO(UUID id, @NotNull String title, String description, @NotNull BigDecimal price) {
    
}
