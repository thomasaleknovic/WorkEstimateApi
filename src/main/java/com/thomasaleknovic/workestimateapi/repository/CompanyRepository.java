package com.thomasaleknovic.workestimateapi.repository;

import com.thomasaleknovic.workestimateapi.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
