package com.thomasaleknovic.workestimateapi.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/estimate")
public class EstimateDownloadController {
    
      @GetMapping("/{id}/download")
    public String downloadEstimate (@PathVariable UUID id) {
        return "workEstimatePdf";
    }
}
