package com.example.whats_happened;

import com.example.whats_happened.entity.Incident;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class GenerateIncident {

    public static void main(String[] args) throws IOException, InterruptedException {
        sendIncident();
    }

    public static Incident createIncident() {
        Incident incident = new Incident();
        incident.setName("name");
        incident.setDescription("description");
        incident.setType("type");
        incident.setLocation("location");
        incident.setStatus("status");
        incident.setCreatedAt(LocalDateTime.now());
        incident.setClosedAt(LocalDateTime.now());
        return incident;
    }

    public static ObjectMapper ConvertObjectToJson() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }

    public static void sendIncident() throws IOException, InterruptedException {
        String requestBody = ConvertObjectToJson().writeValueAsString(createIncident());
        System.out.println(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/rest/post-incident"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.statusCode());
    }

}
