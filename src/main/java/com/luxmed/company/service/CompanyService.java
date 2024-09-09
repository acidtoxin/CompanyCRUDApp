package com.luxmed.company.service;

import com.luxmed.company.model.Company;
import com.luxmed.company.model.Department;
import com.luxmed.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        // Ensure departments and nested entities are properly persisted
        if (company.getDepartments() != null) {
            for (Department department : company.getDepartments()) {
                department.setCompany(company); // Set the reference to Company
            }
        }
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company company) {
        if (companyRepository.findById(id).isPresent()) {
            company.setId(id);
            return companyRepository.save(company);
        }
        return null;
    }

    public void deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        }
    }
}