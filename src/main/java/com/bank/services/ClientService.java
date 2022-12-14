package com.bank.services;

import com.bank.entities.Client;
import com.bank.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void deleteAllById(List<Integer> ids) {
        clientRepository.deleteAllById(ids);
    }
}
