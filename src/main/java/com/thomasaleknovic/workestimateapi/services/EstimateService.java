package com.thomasaleknovic.workestimateapi.services;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.dtos.PaymentMethodDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.models.JobDetails;
import com.thomasaleknovic.workestimateapi.models.PaymentMethod;
import com.thomasaleknovic.workestimateapi.repository.EstimateRepository;

import jakarta.transaction.Transactional;

import org.springframework.web.server.ResponseStatusException;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

 
    @Transactional
    public int getNextServiceOrder() {
        Estimate lastEstimate = estimateRepository.findTopByOrderByCreatedAtDesc();

        int nextServiceOrder = 10001; 

        if (lastEstimate != null) {
            nextServiceOrder = lastEstimate.getServiceOrder() + 1;
        }

        return nextServiceOrder;
    }
    
     
    public List<Estimate> findAllEstimates(){
        return estimateRepository.findAll();
    }

    public Estimate findEstimate (UUID id) {
       return estimateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
               "Orçamento não encontrado!"));
       
    }

    public Estimate createEstimate (EstimateDTO data) {
        Estimate estimate = new Estimate(data);
        estimate.setServiceOrder(getNextServiceOrder());
        return estimateRepository.save(estimate);

    }

    public Estimate updateEstimateInfo(UUID id, EstimateDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);

        if (data.estimateName() != null) {
            estimate.setEstimateName(data.estimateName());
        }
        if (data.customerName() != null) {
            estimate.setCustomerName(data.customerName());
        }
        if (data.cpf() != null) {
            estimate.setCpf(data.cpf());
        }
        if (data.cep() != null) {
            estimate.setCep(data.cep());
        }
        if (data.address() != null) {
            estimate.setAddress(data.address());
        }
        if (data.phone() != null) {
            estimate.setPhone(data.phone());
        }
        if (data.paymentMethod() != null) {
            estimate.setPaymentMethod(data.paymentMethod());
        }
        if (data.observation() != null) {
            estimate.setObservation(data.observation());
        }
        return estimateRepository.save(estimate);
       
    }

    public Estimate addPaymentMethod(UUID id, PaymentMethodDTO paymentMethod) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        estimate.getPaymentMethod().add(new PaymentMethod(paymentMethod));
        return estimateRepository.save(estimate);
    }

    public Estimate updatePaymentMethod(UUID id, PaymentMethodDTO paymentMethod) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        PaymentMethod payment = estimate.getPaymentMethod().stream().filter(item -> item.getId().equals(paymentMethod.id())).findFirst().orElseThrow(EstimateNotFoundException::new);
     
            payment.setPaymentTitle(paymentMethod.paymentTitle());
            payment.setPaymentDescription(paymentMethod.paymentDescription());
       
        return estimateRepository.save(estimate);
    }

    public void deleteEstimate(UUID id) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        estimateRepository.delete(estimate);
        
    }

    public Estimate addJobDetail (UUID id, JobDetailsDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
       
        JobDetails jobDetails = new JobDetails();
        jobDetails.setId(data.id());
        jobDetails.setQuantity(data.quantity());
        jobDetails.setUnitPrice(data.unitPrice());
        jobDetails.setDescription(data.description());
        jobDetails.setPrice(BigDecimal.valueOf(data.quantity()).multiply(data.unitPrice()));
        estimate.getJobDetails().add(jobDetails);
        return estimateRepository.save(estimate);
    }

    public Estimate updateJobDetailInfo (UUID id, JobDetailsDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        JobDetails jobDetails = estimate.getJobDetails().stream().filter(item -> item.getId().equals(data.id())).findFirst().orElseThrow(EstimateNotFoundException::new);
        
        if(data.description() != null) {
            jobDetails.setDescription(data.description());
        }
        if(data.quantity() != null) {
            jobDetails.setQuantity(data.quantity());
        }
        if(data.unitPrice() != null) {
            jobDetails.setUnitPrice(data.unitPrice());
        }

        jobDetails.setPrice(BigDecimal.valueOf(data.quantity()).multiply(data.unitPrice()));
        return estimateRepository.save(estimate);
        

    }


    public Estimate deleteJobDetailInfo (UUID estimateId, UUID jobDetailId ) {
        Estimate estimate = estimateRepository.findById(estimateId).orElseThrow(EstimateNotFoundException::new);
        estimate.getJobDetails().removeIf(item -> item.getId().equals(jobDetailId));
        return estimateRepository.save(estimate);
    }

    
}
