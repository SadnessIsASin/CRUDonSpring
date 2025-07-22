package com.example.whats_happened.service;

import com.example.whats_happened.dto.SmsResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class SMSService {

    @Value("${sms.api-id}")
    private String apiId;
    @Value("${sms.api-uri}")
    private String apiUri;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public SMSService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public SmsResponseDto sendSms(String phoneNumber, String message) {
        try {
            String requestBody = "api_id=" + URLEncoder.encode(apiId, StandardCharsets.UTF_8)
                    + "&to=" + URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8)
                    + "&msg=" + URLEncoder.encode(message, StandardCharsets.UTF_8)
                    + "&json=1";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUri))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("HTTP Status: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            if (response.statusCode() != 200) {
                throw new RuntimeException("HTTP error: " + response.statusCode() + ", body: " + response.body());
            }
            return objectMapper.readValue(response.body(), SmsResponseDto.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка при отправке SMS", e);
        }
    }
}
