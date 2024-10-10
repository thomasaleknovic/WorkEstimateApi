package com.thomasaleknovic.workestimateapi.controllers;

import static com.thomasaleknovic.workestimateapi.util.PdfGenerator.parseThymeleafTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
import com.thomasaleknovic.workestimateapi.services.EstimateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api/estimate")
public class EstimateDownloadController {

     private final EstimateService estimateService;

        public EstimateDownloadController (EstimateService estimateService) {
            this.estimateService = estimateService;

        }
    
      @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadEstimate (@PathVariable UUID id, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        String finalHtml = parseThymeleafTemplate(estimateService.findEstimate(id));
    

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("https://special-cod-59pj9rrgrvjcvrj4-8080.app.github.dev/");

        /* Call convert method */
        HtmlConverter.convertToPdf(finalHtml, target, converterProperties);  

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


    /* Send the response as downloadable PDF */

    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .body(bytes);

                                 
    }
}
