package com.thomasaleknovic.workestimateapi.services;

import com.thomasaleknovic.workestimateapi.dtos.CompanyDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Company.CompanyNotFoundException;
import com.thomasaleknovic.workestimateapi.exceptions.User.UserNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Company;
import com.thomasaleknovic.workestimateapi.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final AuthorizationService authorizationService;

    public CompanyService (CompanyRepository companyRepository, AuthorizationService authorizationService ){
        this.companyRepository = companyRepository;
        this.authorizationService = authorizationService;
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompanyById (UUID id) {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public Company createCompany (CompanyDTO data) {

        if(authorizationService.findUserById(data.ownerID()).isAccountNonExpired()) {
            Company company = new Company(data);
            return companyRepository.save(company);
        } else {
            throw new UserNotFoundException();
        }

    }

    public Company updateCompany (UUID companyId, CompanyDTO data) {
        Company companyToUpdate = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);

        if (data.companyName() != null && !data.companyName().equals(companyToUpdate.getCompanyName())) {
            companyToUpdate.setCompanyName(data.companyName());
        }

        if (data.cnpj() != null && !data.cnpj().equals(companyToUpdate.getCnpj())) {
            companyToUpdate.setCnpj(data.cnpj());
        }

        if (data.cep() != null && !data.cep().equals(companyToUpdate.getCep())) {
            companyToUpdate.setCep(data.cep());
        }

        if (data.address() != null && !data.address().equals(companyToUpdate.getAddress())) {
            companyToUpdate.setAddress(data.address());
        }

        if (data.phoneOne() != null && !data.phoneOne().equals(companyToUpdate.getPhoneOne())) {
            companyToUpdate.setPhoneOne(data.phoneOne());
        }

        if (data.phoneTwo() != null && !data.phoneTwo().equals(companyToUpdate.getPhoneTwo())) {
            companyToUpdate.setPhoneTwo(data.phoneTwo());
        }

        if (data.email() != null && !data.email().equals(companyToUpdate.getEmail())) {
            companyToUpdate.setEmail(data.email());
        }

        if (data.website() != null && !data.website().equals(companyToUpdate.getWebsite())) {
            companyToUpdate.setWebsite(data.website());
        }

        if (data.logo() != null && !data.logo().equals(companyToUpdate.getLogo())) {
            companyToUpdate.setLogo(data.logo());
        }

        if (data.description() != null && !data.description().equals(companyToUpdate.getDescription())) {
            companyToUpdate.setDescription(data.description());
        }

        if (data.socialMedia() != null && !data.socialMedia().equals(companyToUpdate.getSocialMedia())) {
            companyToUpdate.setSocialMedia(data.socialMedia());
        }

        if (data.ownerID() != null && !data.ownerID().equals(companyToUpdate.getOwnerId())) {
            companyToUpdate.setOwnerId(data.ownerID());
        }

        return companyRepository.save(companyToUpdate);


    }


    public void deleteCompany (UUID companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        companyRepository.delete(company);
    }

}
