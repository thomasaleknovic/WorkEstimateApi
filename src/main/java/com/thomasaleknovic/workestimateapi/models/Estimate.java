package com.thomasaleknovic.workestimateapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    private String observation;

    private String paymentMethod;

    private BigDecimal totalPrice = BigDecimal.valueOf(0);

    @OneToMany(cascade = CascadeType.ALL)
    private List<JobDetails> jobDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="customer")
    private Customer customer;


    public Estimate(Customer customer, EstimateDTO data) {
        this.estimateName = data.estimateName();
        this.observation = data.observation();
        this.paymentMethod = data.paymentMethod();
        this.jobDetails = data.jobDetails();
        this.customer = customer;
    }

}
