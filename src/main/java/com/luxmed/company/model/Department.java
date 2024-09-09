package com.luxmed.company.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams;

    public Department() {
    }

    public Department(Long id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }
}