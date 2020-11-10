package com.unlam.library.services;

import com.unlam.library.domain.Client;
import com.unlam.library.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.unlam.library.domain.Status;

import java.util.Date;
import java.util.Optional;



public class ClientServiceTest {

    private static ClientService clientService = ClientService.getInstance();

    @Before
    public void setup() {
        clientService.resetService();
    }
    @Test
    public void upsertTest() {
        Client Martin = new Client();
        clientService.upsert(Martin);
        Assert.assertEquals(1, clientService.findAll().size());
    }
    @Test
    public void upsertTestupdate() {
        Client Carl = new Client();
        clientService.upsert(Carl);
        Carl.setStatus(Status.DISABLED);
        clientService.upsert(Carl);
        Assert.assertEquals(1, clientService.findAll().size());
        Assert.assertEquals(Status.DISABLED, Carl.getStatus());

    }
    @Test
    public void deleteTest() {
        Client Michael = new Client();
        clientService.upsert(Michael);
        clientService.delete(Michael);
        Assert.assertEquals(Status.DISABLED, Michael.getStatus());
    }
    @Test
    public void deleteByIdTest(){
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
        Client Emanuel = new Client ();
        clientService.upsert(Emanuel);
        Assert.assertEquals(Emanuel.getClientId(),clientService.findById(Emanuel.getClientId()).get().getClientId());
    }
    @Test
    public void findByIdentificationTest(){
        Client Emanuel = new Client();
        clientService.upsert(Emanuel);
        Emanuel.setIdentification(1234l);
        Assert.assertEquals(Emanuel.getIdentification(),clientService.findByIdentification(Emanuel.getIdentification()).get().getIdentification());
    }

}
