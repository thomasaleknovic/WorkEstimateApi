package com.thomasaleknovic.workestimateapi.models;


import java.math.BigDecimal;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private BigDecimal price;


    public JobDetails (JobDetailsDTO data) {

        this.title = data.title();
        this.description = data.description();
        this.price = data.price();
    }


    
}
