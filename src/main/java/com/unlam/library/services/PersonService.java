package com.unlam.library.services;

import com.unlam.library.domain.Person;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.HashSet;
import java.util.Set;

public class PersonService implements Storable<Person> {

    private static PersonService personService;
    private Set<Person> persons;
    private Sequence sequence;

    private PersonService() {
        persons = new HashSet<Person>();
        sequence = new Sequence();
    }

    @Override
    public Person save(Person object) {
        return null;
    }

    @Override
    public Boolean delete(Person object) {
        return null;
    }

    @Override
    public Boolean findAll(Person object) {
        return null;
    }

    public Person findByIdentification(Long identification) {
        return new Person();
    }

    public static PersonService getInstance() {
        return personService = personService == null ? new PersonService() : personService;
    }
}
