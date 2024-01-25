package com.thomasaleknovic.workestimateapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.services.EstimateService;

@RestController
@RequestMapping("api/estimate")
public class EstimateController {
    
    @Autowired
    private EstimateService estimateService;


    @GetMapping
    public ResponseEntity<List<Estimate>> listAllEstimates () {
        return ResponseEntity.ok(estimateService.findAllEstimates());

    }
}
