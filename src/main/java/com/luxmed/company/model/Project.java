package com.luxmed.company.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Manager manager;

    public Project() {
    }

    public Project(Long id, String name, Manager manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }
}