package com.thomasaleknovic.workestimateapi.models;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID companyId;

    @NotNull
    private String companyName;

    @Column(unique=true)
    @NotNull
    private String cnpj;

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
    private String phoneOne;

    private String phoneTwo;

    @NotNull
    private String email;
    
    private String website;

    private String logo;

    private String description;

    private String socialMedia;

    @NotNull
    private UUID ownerId;


    public Company(CompanyDTO data) {
        this.companyName = data.companyName();
        this.cnpj = data.cnpj();
        this.address = data.address();
        this.city = data.city();
        this.state = data.state();
        this.zipCode = data.zipCode();
        this.country = data.country();
        this.phoneOne = data.phoneOne();
        this.phoneTwo = data.phoneTwo();
        this.email = data.email();
        this.website = data.website();
        this.logo = data.logo();
        this.description = data.description();
        this.socialMedia = data.socialMedia();
        this.ownerId = data.ownerID();

    }



}
