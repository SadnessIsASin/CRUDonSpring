package com.example.whats_happened.controllers;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.service.IncidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest")
public class MainController {

    private final IncidentService incidentService;

    public MainController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public String helloPage(){
        return "For information on incidents, please use the links:\n" +
                "/get-all-incidents\n" +
                "/get-incident/{id}\n" +
                "/post-incident\n" +
                "/update-incident/{id}\n" +
                "/delete-incident/{id}";
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