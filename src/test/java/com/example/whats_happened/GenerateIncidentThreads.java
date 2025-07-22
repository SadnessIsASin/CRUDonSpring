package com.example.whats_happened;

import com.example.whats_happened.entity.Incident;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateIncidentThreads {

    private static final Logger log = LoggerFactory.getLogger(GenerateIncidentThreads.class);
    private static final int THREAD_COUNT = 5; // количество одновременных потоков
    private static final String URL = "http://localhost:8080/api/incidents/add";

    public static void main(String[] args) {
        sendMultipleIncidents(THREAD_COUNT);
    }

    public static Incident createIncident() {
        Incident incident = new Incident();
        incident.setName("name");
        incident.setDescription("description");
        incident.setType("type");
        incident.setLocation("location");
        incident.setStatus("Открыт");
        incident.setCreatedAt(LocalDateTime.now());
        return incident;
    }

    public static ObjectMapper convertObjectToJson() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }

    public static void sendIncident(int threadId) {
        try {
            String requestBody = convertObjectToJson().writeValueAsString(createIncident());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Поток {} получил статус: {}", threadId, response.statusCode());

        } catch (JsonProcessingException e) {
            log.error("Ошибка сериализации JSON в потоке {}", threadId, e);
        } catch (IOException | InterruptedException e) {
            log.error("Ошибка сети в потоке {}", threadId, e);
        }
    }

    public static void sendMultipleIncidents(int count) {
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        for (int i = 0; i < count; i++) {
            final int threadId = i + 1;
            executorService.execute(() -> {
                log.info("Запуск потока {}", threadId);
                sendIncident(threadId);
            });
        }

        executorService.shutdown();
    }
}