package com.thomasaleknovic.workestimateapi.models;


import java.math.BigDecimal;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String description;

    @NotNull
    private Short quantity;

    @NotNull
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
