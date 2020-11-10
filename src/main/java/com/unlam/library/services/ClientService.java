package com.unlam.library.services;
import com.unlam.library.domain.Author;
import com.unlam.library.domain.Client;
import com.unlam.library.domain.Person;
import com.unlam.library.domain.Status;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.*;

public class ClientService implements Storable<Client> {

    private static ClientService clientService;
    private Set<Client> clients;
    private Sequence sequence;

    private ClientService() {
        clients = new HashSet<Client>();
        sequence = new Sequence();
    }
    
    @Override
    public Client upsert(Client object) {
        if (object.getClientId() == null) {
            object.setClientId(sequence.getSequence());
            object.setCreated_at(new Date());
            object.setStatus(Status.ENABLED);
            Person person = PersonService.getInstance().upsert(object);
            object.updatePerson(person);
            clients.add(object);
            return object;
        } else {
            Optional<Client> clientOptional = findById(object.getClientId());

            if (!clientOptional.isPresent()) {
                System.out.println("[ClientService] Client id not found");
                return null;
            }
            Client client = clientOptional.get();
            client.setStatus(object.getStatus());
            client.setCreated_at(object.getCreated_at());
            client.setClientId(object.getClientId());
            return client;
        }
    }
    @Override
    public Boolean delete(Client object) {
           if(object.getClientId()==null){
               System.out.println("[PersonService] Error in delete, id not found");
               return false;
           }
        return deleteBy(object.getClientId());
    }
    
    @Override
    public Boolean deleteBy(Long id) {
        Optional<Client> client= findById(id);
       if(!client.isPresent()){
           System.out.println("[ClientService] Error in deleteById, Client not found");
           return false;
       }
        client.get().setStatus(Status.DISABLED);
        return true;
    }
    
    @Override
    public List<Client> findAll() {
        return new ArrayList(clients);
    }
    
    @Override
    public Optional<Client> findById(Long id) {
        Client aux = null;
        for (Client client : clients) {
            if (client.getClientId().equals(id)) {
                aux = client;
            }
        }
        if (aux == null) {
            Optional.empty();
        } else {
            Optional<Person> person = PersonService.getInstance().findById(aux.getId());
            if (!person.isPresent()) {
                return Optional.empty();
            }
            aux.updatePerson(person.get());
            return Optional.ofNullable(aux);
        }
        return Optional.empty();
    }

    public Optional<Client> findByIdentification(Long identification){
        for (Client aux : clients) {
            if(identification.equals(aux.getIdentification())){
                return Optional.ofNullable(aux);
            }
        }
        return Optional.empty();
    }
    
    public static ClientService getInstance() {
        return clientService = clientService == null ? new ClientService(): clientService;
    }
    
    public void resetService() {
        sequence=new Sequence();
        clientService=new ClientService();
    }
}
