package com.thomasaleknovic.workestimateapi.dtos;

import java.util.UUID;

public record LoginResponseDTO(String username, UUID userId, String token) {
}
