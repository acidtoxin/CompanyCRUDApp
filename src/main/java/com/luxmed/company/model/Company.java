package com.luxmed.company.model;

import jakarta.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Department> departments;

    public Company() {
    }

    public Company(Long id, String name, List<Department> departments) {
        this.id = id;
        this.name = name;
        this.departments = departments;
    }

}