package com.unlam.library.services;

import com.unlam.library.domain.Person;
import com.unlam.library.domain.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PersonServiceTest {
  public static PersonService personService = PersonService.getInstance();


    @Before
    public void setup() {
        personService.resetService();
    }
    @Test
    public void UpsertTestiIfThePersonDoesntHaveId(){
        Person Martin = new Person();
        personService.upsert(Martin);
        Assert.assertEquals(personService.findAll().size(),1);
    }
    @Test
    public void UpsertTestUpdatePersonStatus() {
        Person Robert = new Person();
        personService.upsert(Robert);
        Robert.setStatus(Status.DISABLED);
        personService.upsert(Robert);
        Assert.assertEquals(personService.findAll().size(),1);
        Assert.assertEquals(Status.DISABLED,Robert.getStatus());
    }
    @Test
    public void deleteByIdTest(){
        Person Juan = new Person();
        personService.upsert(Juan);
        Person auxEnable = personService.upsert(Juan);
        Assert.assertEquals(Status.ENABLED,auxEnable.getStatus());
        personService.deleteBy(auxEnable.getId());
        Optional<Person> auxDisable = personService.findById(auxEnable.getId());
        Assert.assertEquals(Status.DISABLED,auxDisable.get().getStatus());
    }
    @Test
    public void deleteTest(){
        Person Michael = new Person();
        personService.upsert(Michael);
        personService.delete(Michael);
        Assert.assertEquals(Status.DISABLED,Michael.getStatus());
    }
    @Test
    public void findAllTest(){
        Person A = new Person();
        Person b = new Person();
        Person C = new Person();
        Person D = new Person();
        Person E = new Person();
        Person F = new Person();
        personService.upsert(A);
        personService.upsert(b);
        personService.upsert(C);
        personService.upsert(D);
        personService.upsert(E);
        personService.upsert(F);
        Assert.assertEquals(6,personService.findAll().size());
    }

    @Test
    public void findById(){
        Person Mauro = new Person();
        personService.upsert(Mauro);
        Assert.assertEquals(Mauro.getId(),personService.findById(0l).get().getId());
    }

    @Test
    public void findByIdentificationTest(){
        Person Albert = new Person();
        Albert.setIdentification((long)123456);
        personService.upsert(Albert);
        Assert.assertEquals(Albert.getIdentification(),personService.findByIdentification((long)123456).get().getIdentification());
    }




}
