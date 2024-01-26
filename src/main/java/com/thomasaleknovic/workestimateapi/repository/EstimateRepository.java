package com.thomasaleknovic.workestimateapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thomasaleknovic.workestimateapi.models.Estimate;

public interface EstimateRepository extends JpaRepository<Estimate, UUID> {
    
}
