package com.thomasaleknovic.workestimateapi.dtos;

import com.thomasaleknovic.workestimateapi.models.UserRole;

public record RegisterDTO(String username, String password, String email, UserRole role) {
}
