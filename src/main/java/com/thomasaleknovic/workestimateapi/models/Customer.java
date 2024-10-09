package com.thomasaleknovic.workestimateapi.models;

import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;
    @NotNull
    private String name;
    @NotNull
    private String cpf;

    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zipCode;
    @NotNull
    private String country;
    @NotNull
    private UUID companyId;


public Customer(CustomerDTO data) {
    this.name = data.name();
    this.cpf = data.cpf();
    this.email = data.email();
    this.phone = data.phone();
    this.address = data.address();
    this.city = data.city();
    this.state = data.state();
    this.zipCode = data.zipCode();
    this.country = data.country();
    this.companyId = data.companyId();

    }


}