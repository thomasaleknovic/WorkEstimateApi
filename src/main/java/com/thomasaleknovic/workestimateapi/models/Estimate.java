package com.thomasaleknovic.workestimateapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID estimateId;

    private String estimateName;

    private Date createdAt;

    @OneToOne
    private EstimateData estimateData;

    public Estimate(EstimateDTO data) {
        this.estimateName = data.estimateName();
        this.createdAt = data.createdAt();
        this.estimateData = data.estimateData();
    }

}
