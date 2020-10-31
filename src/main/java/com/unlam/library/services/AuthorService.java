package com.unlam.library.services;

import com.unlam.library.domain.Author;
import com.unlam.library.domain.Person;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthorService implements Storable<Author> {

    private static AuthorService authorService;
    private Set<Author> authors;
    private Sequence sequence;

    private AuthorService() {
        authors = new HashSet<Author>();
        sequence = new Sequence();
    }

    @Override
    public Author save(Author object) {
        Person person = PersonService.getInstance().save(object);
        Author author = new Author(sequence.getSequence(), person);

        authors.add(author);

        return author;
    }

    @Override
    public Boolean delete(Author object) {
        return null;
    }

    @Override
    public Boolean findAll(Author object) {
        return null;
    }


    public Author findByIdentification(Long identification) {
        Person person = PersonService.getInstance().findByIdentification(identification);

        if(person != null && person.getId() != null) {
            Optional<Author> author = authors.stream()
                    .filter(a -> a.getId().equals(person.getId()))
                    .findFirst();

            return author
                    .map(value -> value.updatePerson(person))
                    .orElse(null);
        }

        return null;
    }

    public static AuthorService getInstance() {
        return authorService = authorService == null ? new AuthorService() : authorService;
    }
}
