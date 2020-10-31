package com.unlam.library.domain;

import java.util.Date;
import java.util.List;

public class BookRental {

    private Long id;
    private List<Book> books;
    private Client client;
    private StatusBookRental status;
    private Date returnDate;
    private Employee employee;
}
