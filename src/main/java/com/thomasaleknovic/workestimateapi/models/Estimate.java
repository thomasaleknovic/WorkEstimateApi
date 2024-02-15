package com.thomasaleknovic.workestimateapi.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estimates")
public class Estimate {

    @JsonDeserialize
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID estimateId;

    private int serviceOrder;

    @NotNull
    private String estimateName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt = LocalDate.now() ;

    @NotNull
    private String customerName;

    @NotNull
    private String cpf;


    private String cep;

    private String address;

    private String phone;

    private String observation;

    private String paymentMethod;

    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<JobDetails> jobDetails = new ArrayList<>();

    public Estimate(EstimateDTO data) {
        this.estimateName = data.estimateName();
        this.customerName = data.customerName();
        this.cpf = data.cpf();
        this.cep = data.cep();
        this.address = data.address();
        this.phone = data.phone();
        this.observation = data.observation();
        this.paymentMethod = data.paymentMethod();
        this.totalPrice = data.totalPrice();
        this.jobDetails = data.jobDetails();
    }



}
