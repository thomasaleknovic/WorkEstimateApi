package com.thomasaleknovic.workestimateapi.models;

import java.util.UUID;

import org.hibernate.mapping.List;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDataDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class EstimateData {
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID estimateDataId;

    private String customerName;

    private Long cpf;

    private String address;

    private Long phone;

    private List jobDetails;

    public EstimateData (EstimateDataDTO data) {
        this.customerName = data.customerName();
        this.cpf = data.cpf();
        this.address = data.address();
        this.phone = data.phone();
        this.jobDetails = data.jobDetails();
    }
}
