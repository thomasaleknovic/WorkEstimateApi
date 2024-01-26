package com.thomasaleknovic.workestimateapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Estimates")
public class Estimate {

    @JsonDeserialize
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID estimateId;

    private String estimateName;

    private Instant createdAt = Instant.now() ;

    private String customerName;

    private Long cpf;

    private String address;

    private Long phone;

    @OneToMany(cascade = CascadeType.ALL)
    private List<JobDetails> jobDetails;

    public Estimate(EstimateDTO data) {
        this.estimateName = data.estimateName();
        this.customerName = data.customerName();
        this.cpf = data.cpf();
        this.address = data.address();
        this.phone = data.phone();
        this.jobDetails = data.jobDetails();
    }

}
