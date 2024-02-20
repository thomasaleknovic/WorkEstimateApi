package com.thomasaleknovic.workestimateapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;
import com.thomasaleknovic.workestimateapi.models.Company;
import com.thomasaleknovic.workestimateapi.services.CompanyService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompany (@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.findCompanyById(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Company>> listAllCompanies () {
        return ResponseEntity.ok(companyService.findAllCompanies());

    }
   
    @PostMapping("/new")
    public ResponseEntity<Company> createCompany (@RequestBody CompanyDTO data) {
        return ResponseEntity.ok(companyService.createCompany(data));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Company> updateCompany (@PathVariable UUID id, @RequestBody CompanyDTO data) {
        return ResponseEntity.ok(companyService.updateCompanyInfo(id, data));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Company> deleteCompany (@PathVariable UUID id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
    
}
