package com.unlam.library.domain;

public class Author extends Person {

    private Long authorId;
    private Status status;

    public Author() {
        super();
    }

    public Author(Long sequence, Person person) {
        super(person);
        this.authorId = sequence;
    }

    public Author updatePerson(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.identification = person.getIdentification();
        return this;
    }
}
