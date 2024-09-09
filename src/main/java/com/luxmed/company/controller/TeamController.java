package com.luxmed.company.controller;

import com.luxmed.company.model.Department;
import com.luxmed.company.model.Team;
import com.luxmed.company.service.DepartmentService;
import com.luxmed.company.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team, @RequestParam Long departmentId) {
        Optional<Department> departmentOptional = departmentService.getDepartmentById(departmentId);

        if (!departmentOptional.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Or handle error as per your need
        }

        Department department = departmentOptional.get();  // Retrieve the Department object
        team.setDepartment(department);

        Team savedTeam = teamService.save(team);  // Assuming createTeam() method exists
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.findById(id);
        return ResponseEntity.ok(team);
    }
}