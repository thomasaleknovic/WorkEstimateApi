package com.thomasaleknovic.workestimateapi.dtos;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotNull;


public record JobDetailsDTO(@NotNull String title, String description, @NotNull BigDecimal price) {
    
}
