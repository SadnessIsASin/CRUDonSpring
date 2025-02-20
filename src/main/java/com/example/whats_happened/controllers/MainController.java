package com.example.whats_happened.controllers;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.service.IncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/rest")
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
        System.out.println("Incident saved");
    }

    @PutMapping("/update-incident/{id}")
    public Incident updateIncident(@PathVariable UUID id, @RequestBody Incident incident) {
        incident.setId(id);
        return incidentService.updateIncident(incident);
    }

    @DeleteMapping("/delete-incident/{id}")
    public void deleteIncident(@PathVariable UUID id) {
        incidentService.deleteIncidentById(id);
    }

}