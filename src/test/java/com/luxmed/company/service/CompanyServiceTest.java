package com.luxmed.company.service;

import com.luxmed.company.model.Company;
import com.luxmed.company.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCompanies() {
        Company company1 = new Company(1L, "Luxmed", Collections.emptyList());
        Company company2 = new Company(2L, "Medonet", Collections.emptyList());

        when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));

        assertEquals(2, companyService.getAllCompanies().size());
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void testGetCompanyById() {
        Company company = new Company(1L, "Luxmed", Collections.emptyList());

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        assertEquals("Luxmed", companyService.getCompanyById(1L).get().getName());
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateCompany() {
        Company company = new Company(null, "New Company", Collections.emptyList());
        Company savedCompany = new Company(1L, "New Company", Collections.emptyList());

        when(companyRepository.save(company)).thenReturn(savedCompany);

        assertEquals("New Company", companyService.createCompany(company).getName());
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void testUpdateCompany() {
        // Create mock company objects
        Company existingCompany = new Company(1L, "Old Name", Collections.emptyList());
        Company updatedCompany = new Company(1L, "Updated Name", Collections.emptyList());

        // Mock repository behavior
        when(companyRepository.findById(1L)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(updatedCompany)).thenReturn(updatedCompany);

        // Call the service method
        Company result = companyService.updateCompany(1L, updatedCompany);

        // Verify the result
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(companyRepository, times(1)).findById(1L);
        verify(companyRepository, times(1)).save(updatedCompany);
    }

    @Test
    void testDeleteCompany() {
        Company company = new Company(1L, "Company to Delete", Collections.emptyList());

        when(companyRepository.existsById(1L)).thenReturn(true);
        doNothing().when(companyRepository).deleteById(1L);

        companyService.deleteCompany(1L);

        verify(companyRepository, times(1)).existsById(1L);
        verify(companyRepository, times(1)).deleteById(1L);
    }
}
