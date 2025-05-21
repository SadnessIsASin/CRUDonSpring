package com.example.whats_happened.controllers;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.service.IncidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/incidents")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Incident>> getAllIncidents() {
        log.info("Получен GET-запрос /api/incidents/get-all-incidents");
        List<Incident> incidents = incidentService.getAllIncidents();
        log.info("Отправлен список инцидентов ({} элементов)", incidents.size());
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncident(@PathVariable UUID id) {
        log.info("Получен GET-запрос /api/incidents/{}", id);
        Incident incident = incidentService.getIncidentById(id);
        log.info("Инцидент ID {} найден", id);
        return ResponseEntity.ok(incident);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addIncident(@RequestBody Incident incident) {
        log.info("Получен POST-запрос /api/incidents: {}", incident);
        incidentService.saveIncident(incident);
        log.info("Инцидент ID {} успешно сохранён", incident.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateIncident(@PathVariable UUID id, @RequestBody Incident incident) {
        log.info("Получен PATCH-запрос /api/incidents/{}: {}", id, incident);
        incidentService.updateIncident(id, incident);
        log.info("Инцидент ID {} успешно обновлён", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable UUID id) {
        log.info("Получен DELETE-запрос /api/incidents/{}", id);
        incidentService.deleteIncidentById(id);
        log.info("Инцидент ID {} успешно удалён", id);
        return ResponseEntity.noContent().build();
    }
}