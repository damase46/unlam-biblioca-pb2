package com.unlam.library.services;

import com.unlam.library.domain.Client;
import com.unlam.library.interfaces.Storable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ClientService implements Storable<Client> {

    private static ClientService clientService;
    private Set<Client> clients;

    private ClientService() {
        clients = new HashSet<Client>();
    }

    @Override
    public Client upsert(Client object) {
        return null;
    }

    @Override
    public Boolean delete(Client object) {
        return null;
    }

    @Override
    public Boolean deleteBy(Long id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.empty();
    }

    public Optional<Client> findByIdentification(Long identification) {
        // TODO implement
        return Optional.ofNullable(new Client());
    }

    public static ClientService getInstance() {
        return clientService = clientService == null ? new ClientService(): clientService;
    }
}
