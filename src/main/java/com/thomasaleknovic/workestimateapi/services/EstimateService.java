package com.thomasaleknovic.workestimateapi.services;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.repository.EstimateRepository;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;
    
     
    public List<Estimate> findAllEstimates(){
        return estimateRepository.findAll();
    }

    public Estimate findEstimate (UUID id) {
       return estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
       
    }

    public Estimate createEstimate (EstimateDTO data) {
        Estimate estimate = new Estimate(data);
        return estimateRepository.save(estimate);

    }
}
