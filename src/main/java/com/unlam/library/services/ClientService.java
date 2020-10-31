package com.unlam.library.services;

import com.unlam.library.domain.Client;
import com.unlam.library.interfaces.Storable;

import java.util.HashSet;
import java.util.Set;

public class ClientService implements Storable<Client> {

    private static ClientService clientService;
    private Set<Client> clients;

    private ClientService() {
        clients = new HashSet<Client>();
    }

    @Override
    public Client save(Client object) {
        return null;
    }

    @Override
    public Boolean delete(Client object) {
        return null;
    }

    @Override
    public Boolean findAll(Client object) {
        return null;
    }

    public static ClientService getInstance() {
        return clientService = clientService == null ? new ClientService(): clientService;
    }
}
