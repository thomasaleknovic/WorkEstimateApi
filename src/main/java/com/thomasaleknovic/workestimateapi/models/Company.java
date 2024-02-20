package com.thomasaleknovic.workestimateapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {

    public Company(CompanyDTO companyDTO) {
        this.companyName = companyDTO.companyName();
        this.cnpj = companyDTO.cnpj();
        this.cep = companyDTO.cep();
        this.address = companyDTO.address();
        this.phoneOne = companyDTO.phoneOne();
        this.phoneTwo = companyDTO.phoneTwo();
        this.email = companyDTO.email();
        this.website = companyDTO.website();
        this.logo = companyDTO.logo();
        this.description = companyDTO.description();
        this.socialMedia = companyDTO.socialMedia();
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Estimate> estimates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethod = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;



}
