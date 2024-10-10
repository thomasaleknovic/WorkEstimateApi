package com.thomasaleknovic.workestimateapi.controllers;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;
import com.thomasaleknovic.workestimateapi.models.Company;
import com.thomasaleknovic.workestimateapi.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
public class CompanyController {


    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Company>> listAllCompanies () {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> findCompany (@PathVariable UUID companyId) {
        return ResponseEntity.ok(companyService.findCompanyById(companyId));
    }

    @PostMapping("/new")
    public ResponseEntity<Company> createCompany (@RequestBody CompanyDTO data){
        return ResponseEntity.ok(companyService.createCompany(data));
    }

    @PutMapping("/{companyId}/edit")
    public ResponseEntity<Company> updateCompany (@PathVariable UUID companyId, @RequestBody CompanyDTO data) {
        return ResponseEntity.ok(companyService.updateCompany(companyId, data));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Object> deleteCompany (@PathVariable UUID companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }
}
