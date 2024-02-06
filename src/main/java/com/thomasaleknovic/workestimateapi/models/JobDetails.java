package com.thomasaleknovic.workestimateapi.models;


import java.math.BigDecimal;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class JobDetails {

    @Id
    private UUID id;

    private String description;

    private Short quantity;

    private BigDecimal unitPrice;

    private BigDecimal price;


    public JobDetails (JobDetailsDTO data) {

        this.id = data.id();
        this.description = data.description();
        this.quantity = data.quantity();
        this.unitPrice = data.unitPrice();
        this.price = BigDecimal.valueOf(data.quantity()).multiply(data.unitPrice());
    }


    
}
