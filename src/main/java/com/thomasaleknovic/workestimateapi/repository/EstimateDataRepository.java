package com.thomasaleknovic.workestimateapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thomasaleknovic.workestimateapi.models.EstimateData;

public interface EstimateDataRepository extends JpaRepository<EstimateData, UUID> {
    
}
