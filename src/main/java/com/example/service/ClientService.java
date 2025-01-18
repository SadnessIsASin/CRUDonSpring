package com.example.service;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }


public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }


    public Client createClient(Client client) {
        return clientRepository.save(client);
    }


    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }


    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
