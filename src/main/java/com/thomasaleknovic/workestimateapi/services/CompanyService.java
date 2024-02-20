package com.thomasaleknovic.workestimateapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;
import com.thomasaleknovic.workestimateapi.dtos.PaymentMethodDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Company.CompanyNotFoundException;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Company;
import com.thomasaleknovic.workestimateapi.models.PaymentMethod;
import com.thomasaleknovic.workestimateapi.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    

    public List<Company> findAllCompanies(){
        return companyRepository.findAll();
    }

    public Company findCompanyById(UUID id){
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public Company createCompany(CompanyDTO company){
        Company newCompany = new Company(company);
        return companyRepository.save(newCompany);
    }

    public Company updateCompanyInfo(UUID id, CompanyDTO company){
        Company companyToUpdate = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        if(company.companyName() != null){
            companyToUpdate.setCompanyName(company.companyName());
        }
        if(company.cnpj() != null){
            companyToUpdate.setCnpj(company.cnpj());
        }
        if(company.cep() != null){
            companyToUpdate.setCep(company.cep());
        }
        if(company.address() != null){
            companyToUpdate.setAddress(company.address());
        }
        if(company.phoneOne() != null){
            companyToUpdate.setPhoneOne(company.phoneOne());
        }
        if(company.phoneTwo() != null){
            companyToUpdate.setPhoneTwo(company.phoneTwo());
        }
        if(company.email() != null){
            companyToUpdate.setEmail(company.email());
        }
        if(company.website() != null){
            companyToUpdate.setWebsite(company.website());
        }
        if(company.logo() != null){
            companyToUpdate.setLogo(company.logo());
        }
        if(company.description() != null){
            companyToUpdate.setDescription(company.description());
        }
        if(company.socialMedia() != null){
            companyToUpdate.setSocialMedia(company.socialMedia());
        }
        
        return companyRepository.save(companyToUpdate);
        
    }

    public void deleteCompany(UUID id){
        companyRepository.deleteById(id);
    }

    public Company addPaymentMethod(UUID id, PaymentMethodDTO paymentMethod){
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        company.getPaymentMethod().add(new PaymentMethod(paymentMethod));
        return companyRepository.save(company);
    }

    public Company updatePaymentMethod(UUID id, PaymentMethodDTO paymentMethod){
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        PaymentMethod payment = company.getPaymentMethod().stream().filter(item -> item.getId().equals(paymentMethod.id())).findFirst().orElseThrow(CompanyNotFoundException::new);
        
        payment.setPaymentTitle(paymentMethod.paymentTitle());
        payment.setPaymentDescription(paymentMethod.paymentDescription());
        
        return companyRepository.save(company);
    }

    public Company removePaymentMethod(UUID id, UUID paymentId){
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        company.getPaymentMethod().removeIf(item -> item.getId().equals(paymentId));
        return companyRepository.save(company);
    }

}
