package com.unlam.library.services;

import com.unlam.library.domain.Book;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BookService implements Storable<Book> {

    private static BookService bookService;
    private Set<Book> books;
    private Sequence sequence;
	
    private BookService() {
        books = new HashSet<Book>();
        sequence = new Sequence();
    }

    @Override
    public Book upsert(Book object) {
    	if(object!=null && object.getId()==null) {
    		object.setId(sequence.getSequence());
    		books.add(object);
    		return object;
    	}
    	else if(object!=null && object.getId()!=null){
    		deleteBy(object.getId());
    		upsert(object);
    	}
    	return null;
    }

    @Override
    public Boolean delete(Book object) {
        if(object!=null) {
        	return books.remove(object);
        }
    	return false;
    }

    @Override
    public Boolean deleteBy(Long id) {
			if(findById(id).isPresent()) {
				return books.remove(findById(id).get());
			}

        return false;
    }

    @Override
    public List<Book> findAll() {
        List<Book>aux= new ArrayList<Book>();
        	aux.addAll(books);
    	return aux;
    }

    @Override
    public Optional<Book> findById(Long id) {
    	for (Book book : books) {
			if(id.equals(book.getId())) {
				return Optional.ofNullable(book);
			}
    	}
    	return Optional.empty();
    }

    public static BookService getInstance() {
        return bookService = bookService == null ? new BookService() : bookService;
    }
}
