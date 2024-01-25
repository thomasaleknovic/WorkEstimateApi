package com.thomasaleknovic.workestimateapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Estimates")
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID estimateId;

    private String estimateName;

    @CreatedDate
    private Date createdAt;

    private String customerName;

    private Long cpf;

    private String address;

    private Long phone;

    private List<String> jobDetails;

    public Estimate(EstimateDTO data) {
        this.estimateName = data.estimateName();
        this.customerName = data.customerName();
        this.cpf = data.cpf();
        this.address = data.address();
        this.phone = data.phone();
        this.jobDetails = data.jobDetails();
    }

  

}
