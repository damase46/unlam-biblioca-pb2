package com.unlam.library.services;

import com.unlam.library.domain.Author;
import com.unlam.library.domain.Person;
import com.unlam.library.domain.Status;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.ArrayList;
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
        if(object!=null &&object.getId()!=null) {
        	return deleteBy(object.getId());
        }
    	return false;
    }

    @Override
    public Boolean deleteBy(Long id) {	
    	if(findById(id).isPresent()) {
    		findById(id).get().setStatus(Status.DISABLED);
    		return true;
        }
    	return false;
    }

    @Override
    public List<Author> findAll() {
        List<Author>aux=new ArrayList<Author>();
        aux.addAll(authors);
    	return aux;
    }

    @Override
    public Optional<Author> findById(Long id) {

    	for (Author author : authors) {
			if(author.getId().equals(id)) {
				return Optional.ofNullable(author);
			}
		}
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