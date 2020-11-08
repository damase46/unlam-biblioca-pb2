package com.unlam.library.services;

import com.unlam.library.domain.Book;
import com.unlam.library.domain.BookRental;
import com.unlam.library.domain.Client;
import com.unlam.library.domain.Employee;
import com.unlam.library.domain.Status;
import com.unlam.library.domain.StatusBookRental;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRentalService implements Storable<BookRental> {

    private static BookRentalService bookRentalService;
    private List<BookRental> bookRentals;
    private Sequence sequence;

    private BookRentalService() {
        bookRentals = new ArrayList<BookRental>();
        sequence = new Sequence();
    }

    public List<Book> getAllBooksBorrowed() {
        return bookRentals.stream()
                .filter(bookRental -> !bookRental.getStatusRental().equals(StatusBookRental.RETURNED) &&
                        bookRental.getStatus().equals(Status.ENABLED))
                .map(bookRental ->
                    bookRental.getBooks().stream()
                            .map(book -> BookService.getInstance().findById(book.getId()))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toList())
                ).flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public BookRental returnBooksByIdentificationClient(Long identification) {
        Optional<Client> client = ClientService.getInstance().findByIdentification(identification);

        if(!client.isPresent()) {
            System.out.println("[BookRentalService] Error in returnBooksByIdentificationClient, client not found");
            return null;
        }

        return findByClientId(client.get().getClientId());
    }

    public BookRental findByClientId(Long clientId) {
        Optional<BookRental> bookRentalResult = bookRentals.stream()
                .filter(bookRental -> bookRental.getClient().getClientId().equals(clientId))
                .findFirst();

        if(!bookRentalResult.isPresent()) {
            System.out.println("[BookRentalService] Error in findByClientId, BookRental not found");
            return null;
        }

        return bookRentalResult.get();
    }

    public BookRental returnBooksByBookRentalId(Long id) {
        Optional<BookRental> bookRental = findById(id);
            bookRental.get();
        if(!bookRental.isPresent()) {
            System.out.println("[BookRentalService] Error in returnBooksByBookRentalId, id not found");
            return null;
        }

        return bookRental.map(br -> {
            br.setStatusRental(StatusBookRental.RETURNED);
            return br;
        }).get();
    }

    public BookRental prepareOrder(Client client, Employee employee, List<Book> books) {

        if(client == null || client.getId() == null) {
            System.out.println("[BookRentalService] Error in prepareOrder, Client invalid");
            return null;
        }

        if(employee == null || employee.getId() == null) {
            System.out.println("[BookRentalService] Error in prepareOrder, Client invalid");
            return null;
        }

        if(books == null || books.isEmpty() || books.size() > 5 || !isInvalidStock(books)) {
            System.out.println("[BookRentalService] Error in prepareOrder, Books is invalid");
            return null;
        }

        Calendar calReturnDate = Calendar.getInstance();
        calReturnDate.add(Calendar.DAY_OF_MONTH, 10);

        Date returnDate = calReturnDate.getTime();

        return new BookRental(books, client, StatusBookRental.IN_TIME, returnDate, employee, Status.ENABLED);
    }

    private boolean isInvalidStock(List<Book> books) {
        return books.stream().anyMatch(book ->
            LibraryService.getInstance().getStockByBookId(book.getId()) == 0
        );
    }

    @Override
    public BookRental upsert(BookRental object) {

        if(object.getId() == null) {
            object.setId(sequence.getSequence());
            bookRentals.add(object);
            return object;
        } else {
            Optional<BookRental> bookToUpdate = findById(object.getId());

            if(bookToUpdate.isPresent()) {
                return bookToUpdate.map(book -> {
                    book.setStatus(object.getStatus());
                    book.setStatusRental(object.getStatusRental());
                    return book;
                }).get();
            } else {
                bookRentals.add(object);
                return object;
            }
        }
    }

    @Override
    public Boolean delete(BookRental object) {

        if(object.getId() == null) {
            System.out.println("[BookRentalService] Error in delete, id not found");
            return false;
        }

        return deleteBy(object.getId());
    }

    @Override
    public Boolean deleteBy(Long id) {
        Optional<BookRental> bookRental = findById(id);

        if(!bookRental.isPresent()) {
            System.out.println("[BookRentalService] Error in deleteById, Book not found");
            return false;
        }

        bookRental.get().setStatus(Status.DISABLED);
        return true;
    }

    @Override
    public List<BookRental> findAll() {
        return bookRentals;
    }

    @Override
    public Optional<BookRental> findById(Long id) {
        return bookRentals.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public static BookRentalService getInstance() {
        return bookRentalService = bookRentalService == null ? new BookRentalService() : bookRentalService;
    }
}
