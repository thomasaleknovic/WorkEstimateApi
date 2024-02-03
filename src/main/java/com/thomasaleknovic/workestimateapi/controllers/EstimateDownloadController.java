package com.thomasaleknovic.workestimateapi.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.lowagie.text.DocumentException;
import com.thomasaleknovic.workestimateapi.models.Estimate;
import com.thomasaleknovic.workestimateapi.services.EstimateService;
import com.thomasaleknovic.workestimateapi.util.PdfGenerator;
import com.thomasaleknovic.workestimateapi.util.PdfGenerator.*;

@Controller
@RequestMapping("api/estimate")
public class EstimateDownloadController {

     private EstimateService estimateService;

        public EstimateDownloadController (EstimateService estimateService) {
            this.estimateService = estimateService;

        }
    
      @GetMapping("/{id}/download")
    public void downloadEstimate (@PathVariable UUID id) throws DocumentException, IOException {
        // ModelAndView mv = new ModelAndView("workEstimatePdf"); 
        // mv.addObject("estimate", estimateService.findEstimate(id));    
        String finalHtml = PdfGenerator.parseThymeleafTemplate(estimateService.findEstimate(id));
        PdfGenerator.generatePdfFromHtml(finalHtml);
                                 
    }
}
