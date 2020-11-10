package com.unlam.library.services;

import com.unlam.library.domain.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.unlam.library.domain.Status;

import java.util.Optional;



public class ClientServiceTest {


    @Before
    public void setup() {
        ClientService clientService = ClientService.getInstance();
        clientService.resetService();
    }

    @Test
    public void upsertTest() {
        ClientService clientService = ClientService.getInstance();
        Client Martin = new Client();
        clientService.upsert(Martin);
        Assert.assertEquals(1, clientService.findAll().size());
    }
    @Test
    public void upsertTestupdate() {
        ClientService clientService = ClientService.getInstance();
        Client Carl = new Client();
        clientService.upsert(Carl);
        Carl.setStatus(Status.DISABLED);
        clientService.upsert(Carl);
        Assert.assertEquals(1, clientService.findAll().size());
        Assert.assertEquals(Status.DISABLED, Carl.getStatus());

    }
    @Test
    public void deleteTest() {
        ClientService clientService = ClientService.getInstance();
        Client Michael = new Client();
        clientService.upsert(Michael);
        clientService.delete(Michael);
        Assert.assertEquals(Status.DISABLED, Michael.getStatus());
    }
    @Test
    public void deleteByIdTest(){
        ClientService clientService = ClientService.getInstance();
        Client Juan = new Client();
        clientService.upsert(Juan);
        Client auxEnable = clientService.upsert(Juan);
        Assert.assertEquals(Status.ENABLED,auxEnable.getStatus());
        clientService.deleteBy(auxEnable.getId());
        Optional<Client> auxDisable = clientService.findById(auxEnable.getId());
        Assert.assertEquals(Status.DISABLED,auxDisable.get().getStatus());
}
    @Test
    public void findByIdTest(){
        ClientService clientService = ClientService.getInstance();
        Client Emanuel = new Client ();
        clientService.upsert(Emanuel);
        Assert.assertEquals(Emanuel.getClientId(),clientService.findById(Emanuel.getClientId()).get().getClientId());
    }
    @Test
    public void findByIdentificationTest(){
        ClientService clientService = ClientService.getInstance();
        Client Emanuel = new Client();
        clientService.upsert(Emanuel);
        Emanuel.setIdentification(1234l);
        Assert.assertEquals(Emanuel.getIdentification(),clientService.findByIdentification(Emanuel.getIdentification()).get().getIdentification());
    }

}
