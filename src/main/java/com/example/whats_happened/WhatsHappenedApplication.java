package com.example.whats_happened;

import com.example.whats_happened.entity.Incident;
import com.example.whats_happened.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class WhatsHappenedApplication {


    public static void main(String[] args) {
		SpringApplication.run(WhatsHappenedApplication.class, args);
	}

	private final IncidentService incidentService;

	@Autowired
	public WhatsHappenedApplication(IncidentService incidentService) {
		this.incidentService = incidentService;
	}


	@Bean
	public CommandLineRunner demo() {
		return args -> {
			Incident incident = new Incident();
			incident.setName("name");
			incident.setDescription("description");
			incident.setType("type");
			incident.setLocation("location");
			incident.setStatus("status");
			incident.setLocation("location");
			incident.setCreatedAt(LocalDateTime.now());
			incident.setClosedAt(LocalDateTime.now());
			incidentService.saveIncident(incident);

            System.out.println("Инцидент успешно сохранен, id " + incident.getId());
		};
	}



}
