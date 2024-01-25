package com.thomasaleknovic.workestimateapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Estimate {

    @Id
    @GeneratedValue
    public UUID estimateId;

    public String estimateName;

    public Date createdAt;

    public EstimateData estimateData;

    public Estimate(EstimateDTO data) {
        this.estimateName = data.estimateName();
        this.createdAt = data.createdAt();
        this.estimateData - data.estimateData();
    }

}
