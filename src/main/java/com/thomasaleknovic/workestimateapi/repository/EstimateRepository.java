package com.thomasaleknovic.workestimateapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thomasaleknovic.workestimateapi.models.Estimate;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, UUID> {
    
    Estimate findFirstByOrderByServiceOrderDesc();
}
