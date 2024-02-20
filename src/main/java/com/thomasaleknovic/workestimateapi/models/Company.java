package com.thomasaleknovic.workestimateapi.models;

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

    public Company(String companyName, String cnpj, String cep, String address, String phoneOne, String phoneTwo, String email, String website, String logo, String description, String socialMedia) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.cep = cep;
        this.address = address;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.description = description;
        this.socialMedia = socialMedia;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID companyId;

    @NotNull
    private String companyName;

    @Column(unique=true)
    @NotNull
    private String cnpj;

    @NotNull
    private String cep;

    @NotNull
    private String address;

    @NotNull
    private String phoneOne;

    private String phoneTwo;

    @NotNull
    private String email;
    
    private String website;

    private String logo;

    private String description;

    private String socialMedia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;



}
