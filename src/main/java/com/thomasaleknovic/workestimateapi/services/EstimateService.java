package com.thomasaleknovic.workestimateapi.services;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.models.JobDetails;
import com.thomasaleknovic.workestimateapi.repository.EstimateRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

 
    
     
    public List<Estimate> findAllEstimates(){
        return estimateRepository.findAll();
    }

    public Estimate findEstimate (UUID id) {
       return estimateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
               "Orçamento não encontrado!"));
       
    }

    public Estimate createEstimate (EstimateDTO data) {
        Estimate estimate = new Estimate(data);
        return estimateRepository.save(estimate);

    }

    public Estimate addJobDetail (UUID id, JobDetailsDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        estimate.getJobDetails().add(new JobDetails(data));
        return estimateRepository.save(estimate);
    }

    public Estimate updateJobDetailInfo (UUID id, JobDetailsDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        JobDetails jobDetails = estimate.getJobDetails().stream().filter(item -> item.getId().equals(data.id())).findFirst().orElseThrow(EstimateNotFoundException::new);
        jobDetails.setTitle(data.title());
        jobDetails.setDescription(data.description());
        jobDetails.setPrice(data.price());
        return estimateRepository.save(estimate);
        

    }

    public Estimate updateEstimateInfo(UUID id, EstimateDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        estimate.setEstimateName(data.estimateName());
        estimate.setCustomerName(data.customerName());
        estimate.setCpf(data.cpf());
        estimate.setAddress(data.address());
        estimate.setPhone(data.phone());
        return estimateRepository.save(estimate);
       
    }

    public void deleteEstimate(UUID id) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        estimateRepository.delete(estimate);
        
    }

    public Estimate deleteJobDetailInfo (UUID estimateId, UUID jobDetailId ) {
        Estimate estimate = estimateRepository.findById(estimateId).orElseThrow(EstimateNotFoundException::new);
        estimate.getJobDetails().removeIf(item -> item.getId().equals(jobDetailId));
        return estimateRepository.save(estimate);
    }

    
}
