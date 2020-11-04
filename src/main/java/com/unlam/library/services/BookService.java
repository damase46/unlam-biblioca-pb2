package com.unlam.library.services;

import com.unlam.library.domain.Book;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

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
        return null;
    }

    @Override
    public Boolean delete(Book object) {
        return null;
    }

    @Override
    public Boolean deleteBy(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    public static BookService getInstance() {
        return bookService = bookService == null ? new BookService() : bookService;
    }
}
