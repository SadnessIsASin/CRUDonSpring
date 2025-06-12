package com.example.whats_happened.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class StartupInfoLogger implements CommandLineRunner {

    private final WebServerApplicationContext webServerAppContext;

    public StartupInfoLogger(WebServerApplicationContext webServerAppContext) {
        this.webServerAppContext = webServerAppContext;
    }

    @Override
    public void run(String... args) throws UnknownHostException {
        int port = webServerAppContext.getWebServer().getPort();
        String host = InetAddress.getLocalHost().getHostAddress();
        String hostName = InetAddress.getLocalHost().getHostName();

        log.info("✅ Application started successfully!");
        log.info("🔗 Access URL: http://{}:{}/", host, port);
        log.info("🏠 Host name: {}", hostName);
        log.info("⚙️ Server port: {}", port);
    }
}