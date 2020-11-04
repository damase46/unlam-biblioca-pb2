package com.unlam.library.domain;

import java.util.Date;

public class Book {

    private Long id;
    private String name;
    private Author author;
    private Gender gender;
    private Date publicationDate;
    private Editorial editorial;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
