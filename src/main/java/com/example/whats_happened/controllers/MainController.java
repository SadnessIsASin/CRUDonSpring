package com.example.whats_happened.controllers;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.service.IncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    private final IncidentService incidentService;

    public MainController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/get-all-incidents")
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @GetMapping("/get-incident/{id}")
    public Incident getIncident(@PathVariable UUID id) {
        return incidentService.getIncidentById(id);
    }

    @PostMapping("/post-incident")
    public void addIncident(@RequestBody Incident incident) {
        incidentService.saveIncident(incident);
    }
    
    @PatchMapping("/update-incident/{id}")
    public void updateIncident(@PathVariable UUID id, @RequestBody Incident incident) {
        Incident existingIncident = incidentService.getIncidentById(id);

        if(incident.getName() != null) {
            existingIncident.setName(incident.getName());
        }
        if(incident.getDescription() != null) {
            existingIncident.setDescription(incident.getDescription());
        }
        if (incident.getStatus() != null) {
            existingIncident.setStatus(incident.getStatus());
        }

        incidentService.saveIncident(existingIncident);
    }

    @DeleteMapping("/delete-incident/{id}")
    public void deleteIncident(@PathVariable UUID id) {
        incidentService.deleteIncidentById(id);
    }

}