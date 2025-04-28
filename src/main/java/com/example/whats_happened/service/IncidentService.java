package com.example.whats_happened.service;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.repository.IncidentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(UUID id){
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident saveIncident(Incident incident){
        if(incident.getCreatedAt() == null){
            incident.setCreatedAt(LocalDateTime.now());
        }
        return incidentRepository.save(incident);
    }

    public void deleteIncidentById(UUID id){
        if(incidentRepository.findById(id).isPresent()){
            incidentRepository.deleteById(id);
        }
    }

}
