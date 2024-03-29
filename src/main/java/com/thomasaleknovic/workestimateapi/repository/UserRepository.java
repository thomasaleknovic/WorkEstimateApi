package com.thomasaleknovic.workestimateapi.repository;

import com.thomasaleknovic.workestimateapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByUsername(String username);
}
