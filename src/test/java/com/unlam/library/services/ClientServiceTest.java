package com.unlam.library.services;

import com.unlam.library.domain.Client;
import com.unlam.library.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientServiceTest {

    private ClientService service;

    @Before
    public  void setup(){
        service=ClientService.getInstance();
        service.resetService();
    }

    @Test
    public void upsertTest(){
        Date date_at=new Date();
        Client Martin = new Client((long) 1234,date_at);



        ClientService clientService=ClientService.getInstance();

        clientService.upsert(Martin);

        assertEquals(clientService.findAll().size(),1);






    }
}
