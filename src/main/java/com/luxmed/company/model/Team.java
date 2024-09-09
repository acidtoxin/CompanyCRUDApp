package com.luxmed.company.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Project project;

    public Team() {
    }

    public Team(Long id, String name, Department department, Project project) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.project = project;
    }
}