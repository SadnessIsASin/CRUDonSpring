package com.example.whats_happened.service;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getAllIncidents(){
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(UUID id){
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident saveIncident(Incident incident){
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(Incident incident){
        return incidentRepository.save(incident);
    }

    public void deleteIncidentById(UUID id){
        incidentRepository.deleteById(id);
    }

}
