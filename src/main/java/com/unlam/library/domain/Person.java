package com.unlam.library.domain;

public class Person {

    protected Long id;
    protected String name;
    protected String lastName;
    protected Long identification;

    public Person() { }

    public Person(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.lastName = person.lastName;
        this.identification = person.identification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
        this.identification = identification;
    }
}
