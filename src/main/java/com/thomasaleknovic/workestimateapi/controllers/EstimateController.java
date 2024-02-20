package com.thomasaleknovic.workestimateapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.dtos.PaymentMethodDTO;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.services.EstimateService;

@RestController
@RequestMapping("api/estimate")
public class EstimateController {
   
    private EstimateService estimateService;

    public EstimateController (EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estimate> findEstimate (@PathVariable UUID id) {
        return ResponseEntity.ok(estimateService.findEstimate(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Estimate>> listAllEstimates () {
        return ResponseEntity.ok(estimateService.findAllEstimates());

    }
   
    @PostMapping("/new")
    public ResponseEntity<Estimate> createEstimate (@RequestBody EstimateDTO data) {
        return ResponseEntity.ok(estimateService.createEstimate(data));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Estimate> updateEstimate (@PathVariable UUID id, @RequestBody EstimateDTO data) {
        return ResponseEntity.ok(estimateService.updateEstimateInfo(id, data));
    }

    // @PutMapping("/{id}/edit/payment")
    // public ResponseEntity<Estimate> updatePaymentMethod (@PathVariable UUID id, @RequestBody PaymentMethodDTO data) {
    //     return ResponseEntity.ok(estimateService.updatePaymentMethod(id, data));
    // }

    // @PostMapping("/{id}/edit/payment")
    // public ResponseEntity<Estimate> insertPaymentMethod (@PathVariable UUID id, @RequestBody PaymentMethodDTO data) {
    //     return ResponseEntity.ok(estimateService.addPaymentMethod(id, data));
    // }
    
    @PostMapping("/{id}/edit/details")
    public ResponseEntity<Estimate> insertJobDetail (@PathVariable UUID id, @RequestBody JobDetailsDTO data) {

        return ResponseEntity.ok(estimateService.addJobDetail(id, data));
    }

    @PutMapping("/{id}/edit/details")
    public ResponseEntity<Estimate> updateJobDetail (@PathVariable UUID id, @RequestBody JobDetailsDTO data) {

        return ResponseEntity.ok(estimateService.updateJobDetailInfo(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstimate (@PathVariable UUID id) {
        estimateService.deleteEstimate(id);
        return ResponseEntity.noContent().build();
    } 

    @DeleteMapping("/{id}/edit/details")
    public ResponseEntity<Estimate> deleteJobDetail (@PathVariable UUID id, @RequestBody UUID jobDetailId ) {
        return ResponseEntity.ok(estimateService.deleteJobDetailInfo(id, jobDetailId));
       
    } 

  
}
