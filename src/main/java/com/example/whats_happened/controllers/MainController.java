package com.example.whats_happened.controllers;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.service.IncidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest")
@Slf4j
public class MainController {

    private final IncidentService incidentService;

    public MainController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/get-all-incidents")
    public List<Incident> getAllIncidents() {
        log.info("All incidents received");
        return incidentService.getAllIncidents();
    }

    @GetMapping("/get-incident/{id}")
    public Incident getIncident(@PathVariable UUID id) {
        log.info("Incident received with id=" + id);
        return incidentService.getIncidentById(id);
    }

    @PostMapping("/post-incident")
    public void addIncident(@RequestBody Incident incident) {
        incidentService.saveIncident(incident);
        log.info("Incident saved, id=" + incident.getId());
    }

    @PutMapping("/update-incident/{id}")
    public Incident updateIncident(@PathVariable UUID id, @RequestBody Incident incident) {
        incident.setId(id);
        log.info("Incident updated with id=" + id);
        return incidentService.updateIncident(incident);

    }

    @DeleteMapping("/delete-incident/{id}")
    public void deleteIncident(@PathVariable UUID id) {
        incidentService.deleteIncidentById(id);
        log.info("Incident deleted with id=" + id);
    }

}