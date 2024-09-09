package com.luxmed.company.controller;

import com.luxmed.company.model.Department;
import com.luxmed.company.model.Team;
import com.luxmed.company.service.DepartmentService;
import com.luxmed.company.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TeamController.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TeamService teamService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    @Test
    void testCreateTeamSuccess() throws Exception {
        Department department = new Department();
        department.setId(1L);
        Team team = new Team();
        team.setId(1L);
        team.setName("Backend Team");

        when(departmentService.getDepartmentById(anyLong())).thenReturn(Optional.of(department));
        when(teamService.save(any(Team.class))).thenReturn(team);

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Backend Team\"}")
                        .param("departmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Backend Team"));

        verify(departmentService, times(1)).getDepartmentById(anyLong());
        verify(teamService, times(1)).save(any(Team.class));
    }

    @Test
    void testCreateTeamDepartmentNotFound() throws Exception {
        when(departmentService.getDepartmentById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Backend Team\"}")
                        .param("departmentId", "1"))
                .andExpect(status().isBadRequest());

        verify(departmentService, times(1)).getDepartmentById(anyLong());
        verify(teamService, never()).save(any(Team.class));
    }

    @Test
    void testGetTeamById() throws Exception {
        Team team = new Team();
        team.setId(1L);
        team.setName("Backend Team");

        when(teamService.findById(anyLong())).thenReturn(team);

        mockMvc.perform(get("/teams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Backend Team"));

        verify(teamService, times(1)).findById(anyLong());
    }
}