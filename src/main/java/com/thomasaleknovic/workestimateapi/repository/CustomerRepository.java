package com.thomasaleknovic.workestimateapi.repository;

import com.thomasaleknovic.workestimateapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
