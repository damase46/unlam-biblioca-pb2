package com.unlam.library.services;

import com.unlam.library.domain.Author;
import com.unlam.library.domain.Person;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.HashSet;
import java.util.List;
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
    public Author upsert(Author object) {
        Person person = PersonService.getInstance().upsert(object);
        Author author = new Author(sequence.getSequence(), person);

        authors.add(author);

        return author;
    }

    @Override
    public Boolean delete(Author object) {
        return null;
    }

    @Override
    public Boolean deleteBy(Long id) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.empty();
    }

    public Author findByIdentification(Long identification) {
        Optional<Person> person = PersonService.getInstance().findByIdentification(identification);

        if(person.isPresent() && person.get().getId() != null) {
            Optional<Author> author = authors.stream()
                    .filter(a -> a.getId().equals(person.get().getId()))
                    .findFirst();

            return (Author) author
                    .map(value -> value.updatePerson(person.get()))
                    .orElse(null);
        }

        return null;
    }

    public static AuthorService getInstance() {
        return authorService = authorService == null ? new AuthorService() : authorService;
    }
}
