package com.luxmed.company.service;

import com.luxmed.company.model.Team;
import com.luxmed.company.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team findById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.orElseThrow(() -> new RuntimeException("Team not found with id " + id));
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}