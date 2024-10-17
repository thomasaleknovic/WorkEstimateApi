package com.thomasaleknovic.workestimateapi.services;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.thomasaleknovic.workestimateapi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.models.JobDetails;
import com.thomasaleknovic.workestimateapi.repository.EstimateRepository;

import jakarta.transaction.Transactional;


@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    private CustomerService customerService;


 
    @Transactional
    public int getNextServiceOrder() {

        Estimate lastEstimate = estimateRepository.findFirstByOrderByServiceOrderDesc();

        int nextServiceOrder = 10001;


        if (lastEstimate != null) {
            nextServiceOrder = lastEstimate.getServiceOrder() + 1;
        }

        return nextServiceOrder;
    }

    @Transactional
    public BigDecimal getTotalPrice(Estimate estimate) {


        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (JobDetails item : estimate.getJobDetails()) {
            totalPrice = totalPrice.add(item.getPrice());
        }

        return totalPrice;
    }
    
     
    public List<Estimate> findAllEstimates(){
        return estimateRepository.findAll();
    }

    public Estimate findEstimate (UUID id) {
       return estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
       
    }

    public Estimate createEstimate (EstimateDTO data) {

        Customer customer = customerService.findCustomer(data.customerId());
        Estimate estimate = new Estimate(customer, data);
        estimate.setServiceOrder(getNextServiceOrder());
        return estimateRepository.save(estimate);

    }

    public Estimate addJobDetail (UUID id, JobDetailsDTO data) {
        Estimate estimate = estimateRepository.findById(id)
                .orElseThrow(EstimateNotFoundException::new);
       
        JobDetails jobDetails = new JobDetails();
        jobDetails.setId(data.id());
        jobDetails.setQuantity(data.quantity());
        jobDetails.setUnitPrice(data.unitPrice());
        jobDetails.setDescription(data.description());
        jobDetails.setPrice(BigDecimal.valueOf(data.quantity()).multiply(data.unitPrice()));
        estimate.getJobDetails().add(jobDetails);
        estimate.setTotalPrice(getTotalPrice(estimate));
        return estimateRepository.save(estimate);
    }

    public Estimate updateJobDetailInfo (UUID id, JobDetailsDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);

        JobDetails jobDetails = estimate.getJobDetails().stream().filter(
                item -> item.getId().equals(data.id())).findFirst()
                .orElseThrow(EstimateNotFoundException::new);

        if (!data.quantity().equals(jobDetails.getQuantity())) {
            jobDetails.setQuantity(data.quantity());
        }

        if (!data.unitPrice().equals(jobDetails.getUnitPrice())) {
            jobDetails.setUnitPrice(data.unitPrice());
        }

        if (!data.description().equals(jobDetails.getDescription())) {
            jobDetails.setDescription(data.description());
        }

        jobDetails.setPrice(BigDecimal.valueOf(data.quantity()).multiply(data.unitPrice()));
        estimate.setTotalPrice(getTotalPrice(estimate));
        return estimateRepository.save(estimate);
        

    }

    public Estimate updateEstimateInfo(UUID id, EstimateDTO data) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        Customer customer = customerService.findCustomer(data.customerId());

        if (!data.estimateName().equals(estimate.getEstimateName())) {
            estimate.setEstimateName(data.estimateName());
        }

        if (!data.paymentMethod().equals(estimate.getPaymentMethod())) {
            estimate.setPaymentMethod(data.paymentMethod());
        }

        if (data.observation() != null && !data.observation().equals(estimate.getObservation())) {
            estimate.setObservation(data.observation());
        }

        if (!estimate.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
            estimate.setCustomer(customer);
        }

        return estimateRepository.save(estimate);
       
    }

    public void deleteEstimate(UUID id) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(EstimateNotFoundException::new);
        estimateRepository.delete(estimate);
        
    }

    public Estimate deleteJobDetailInfo (UUID estimateId, UUID jobDetailId ) {
        Estimate estimate = estimateRepository.findById(estimateId).orElseThrow(EstimateNotFoundException::new);
        estimate.getJobDetails().removeIf(item -> item.getId().equals(jobDetailId));
        estimate.setTotalPrice(getTotalPrice(estimate));
        return estimateRepository.save(estimate);
    }

    
}
