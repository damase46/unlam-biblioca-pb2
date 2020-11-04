package com.unlam.library.services;

import com.unlam.library.domain.Person;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    public Person upsert(Person object) {
        return null;
    }

    @Override
    public Boolean delete(Person object) {
        return null;
    }

    @Override
    public Boolean deleteBy(Long id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.empty();
    }

    public Optional<Person> findByIdentification(Long identification) {
        // TODO implement
        return Optional.ofNullable(new Person());
    }

    public static PersonService getInstance() {
        return personService = personService == null ? new PersonService() : personService;
    }
}
