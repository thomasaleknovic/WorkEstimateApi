package com.thomasaleknovic.workestimateapi.repository;

import com.thomasaleknovic.workestimateapi.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
