package com.example;

import com.example.entity.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {


	private final ClientService clientService;


	@Autowired
	public CrudApplication(ClientService clientService) {
		this.clientService = clientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo() {
		return args -> {
			Client client = new Client();
			client.setName("John");
			client.setEmail("john@example.com");
			client.setPhone("123456789");
			clientService.createClient(client);

			Client savedClient = clientService.getClientById(client.getId());
			System.out.println("Saved Client: " + savedClient.getName());

		};
	}



}


