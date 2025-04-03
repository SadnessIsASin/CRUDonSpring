package com.example.whats_happened.service;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.repository.IncidentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class IncidentService {

    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getAllIncidents(){
        log.info("All incidents received");
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(UUID id){
        log.info("Incident received with id=" + id);
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident saveIncident(Incident incident){
        log.info("Incident saved, id=" + incident.getId());
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(Incident incident){
        log.info("Incident updated with id=" + incident.getId());
        return incidentRepository.save(incident);
    }

    public void deleteIncidentById(UUID id){
        log.info("Incident deleted with id=" + id);
        incidentRepository.deleteById(id);
    }

}
