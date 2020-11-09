package com.unlam.library.domain;

import java.util.Date;
import java.util.List;

public class BookRental implements Cloneable{

    private Long id;
    private List<Book> books;
    private Client client;
    private StatusBookRental statusRental;
    private Date returnDate;
    private Employee employee;
    private Status status;

    public BookRental() {
    }

    public BookRental(List<Book> books, Client client, StatusBookRental statusRental, Date returnDate, Employee employee, Status status) {
        this.books = books;
        this.client = client;
        this.statusRental = statusRental;
        this.returnDate = returnDate;
        this.employee = employee;
        this.status = status;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public StatusBookRental getStatusRental() {
        return statusRental;
    }

    public void setStatusRental(StatusBookRental statusRental) {
        this.statusRental = statusRental;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
