package com.luxmed.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxmed.company.model.Company;
import com.luxmed.company.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    void testGetAllCompanies() throws Exception {
        List<Company> companies = Arrays.asList(
                new Company(1L, "Luxmed", Collections.emptyList()),
                new Company(2L, "Medonet", Collections.emptyList())
        );

        when(companyService.getAllCompanies()).thenReturn(companies);

        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Luxmed"))
                .andExpect(jsonPath("$[1].name").value("Medonet"));

        verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    void testGetCompanyById() throws Exception {
        Company company = new Company(1L, "Luxmed", Collections.emptyList());

        when(companyService.getCompanyById(1L)).thenReturn(Optional.of(company));

        mockMvc.perform(get("/api/companies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Luxmed"));

        verify(companyService, times(1)).getCompanyById(1L);
    }

    @Test
    void testGetCompanyByIdNotFound() throws Exception {
        when(companyService.getCompanyById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/companies/1"))
                .andExpect(status().isNotFound());

        verify(companyService, times(1)).getCompanyById(1L);
    }

    @Test
    void testCreateCompany() throws Exception {
        Company company = new Company(null, "New Company", Collections.emptyList());
        Company createdCompany = new Company(1L, "New Company", Collections.emptyList());

        when(companyService.createCompany(any(Company.class))).thenReturn(createdCompany);

        mockMvc.perform(post("/api/companies")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Company"));

        verify(companyService, times(1)).createCompany(any(Company.class));
    }

    @Test
    void testUpdateCompany() throws Exception {
        Company company = new Company(1L, "Updated Company", Collections.emptyList());

        when(companyService.updateCompany(any(Long.class), any(Company.class))).thenReturn(company);

        mockMvc.perform(put("/api/companies/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Company"));

        verify(companyService, times(1)).updateCompany(any(Long.class), any(Company.class));
    }

    @Test
    void testDeleteCompany() throws Exception {
        mockMvc.perform(delete("/api/companies/1"))
                .andExpect(status().isNoContent());

        verify(companyService, times(1)).deleteCompany(1L);
    }
}
