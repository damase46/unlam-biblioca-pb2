package com.unlam.library.services;

import com.unlam.library.domain.BookRental;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.ArrayList;
import java.util.List;

public class BookRentalService implements Storable<BookRental> {

    private static BookRentalService bookRentalService;
    private List<BookRental> bookRentals;
    private Sequence sequence;

    private BookRentalService() {
        bookRentals = new ArrayList<BookRental>();
        sequence = new Sequence();
    }

    @Override
    public BookRental save(BookRental object) {
        return null;
    }

    @Override
    public Boolean delete(BookRental object) {
        return null;
    }

    @Override
    public Boolean findAll(BookRental object) {
        return null;
    }

    public static BookRentalService getInstance() {
        return bookRentalService = bookRentalService == null ? new BookRentalService() : bookRentalService;
    }
}
