package com.thomasaleknovic.workestimateapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.thomasaleknovic.workestimateapi.dtos.EstimateDTO;
import com.thomasaleknovic.workestimateapi.dtos.JobDetailsDTO;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.models.JobDetails;
import com.thomasaleknovic.workestimateapi.services.EstimateService;

@RestController
@RequestMapping("api/estimate")
public class EstimateController {
   
    private EstimateService estimateService;

    public EstimateController (EstimateService estimateService) {
        this.estimateService = estimateService;
    }


    @GetMapping("/findall")
    public ResponseEntity<List<Estimate>> listAllEstimates () {
        return ResponseEntity.ok(estimateService.findAllEstimates());

    }
    @GetMapping("/find")
    public ResponseEntity<Estimate> findEstimate (@RequestBody UUID id) {
        return ResponseEntity.ok(estimateService.findEstimate(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Estimate> createEstimate (@RequestBody EstimateDTO data) {
        return ResponseEntity.ok(estimateService.createEstimate(data));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Estimate> updateEstimate (@PathVariable UUID id, @RequestBody EstimateDTO data) {
        return ResponseEntity.ok(estimateService.updateEstimateInfo(id, data));
    }
    
    @PutMapping("/{id}/edit/insert/details")
    public ResponseEntity<Estimate> insertJobDetail (@PathVariable UUID id, @RequestBody JobDetailsDTO data) {

        return ResponseEntity.ok(estimateService.addJobDetail(id, data));
    }

}
