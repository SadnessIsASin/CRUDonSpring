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
}
