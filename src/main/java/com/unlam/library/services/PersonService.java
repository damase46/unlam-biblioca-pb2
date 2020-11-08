package com.unlam.library.services;

import com.unlam.library.domain.Person;
import com.unlam.library.domain.Status;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.ArrayList;
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

        if(object.getId() == null) {
            object.setId(sequence.getSequence());
            object.setStatus(Status.ENABLED);
            persons.add(object);
            return object;
        } else {
            Optional<Person> personOptional = findById(object.getId());

            if(!personOptional.isPresent()) {
                System.out.println("[PersonService] person id not found");
                return null;
            }

            Person person = personOptional.get();
            person.setStatus(object.getStatus());
            person.setName(object.getName());
            person.setLastName(object.getLastName());
            person.setIdentification(object.getIdentification());
            return person;
        }
    }

    @Override
    public Boolean delete(Person object) {
        if(object.getId() == null) {
            System.out.println("[PersonService] Error in delete, id not found");
            return false;
        }

        return deleteBy(object.getId());
    }

    @Override
    public Boolean deleteBy(Long id) {
        Optional<Person> person = findById(id);

        if(!person.isPresent()) {
            System.out.println("[PersonService] Error in deleteById, Person not found");
            return false;
        }

        person.get().setStatus(Status.DISABLED);
        return true;
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public Optional<Person> findById(Long id) {

        for (Person person: persons) {
            if(person.getId().equals(id)) {
                return Optional.of(person);
            }
        }

        return Optional.empty();
    }

    public Optional<Person> findByIdentification(Long identification) {
        for (Person person: persons) {
            if(person.getIdentification().equals(identification)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public static PersonService resetService() {
        return personService = new PersonService();
    }

    public static PersonService getInstance() {
        return personService = personService == null ? new PersonService() : personService;
    }
}
