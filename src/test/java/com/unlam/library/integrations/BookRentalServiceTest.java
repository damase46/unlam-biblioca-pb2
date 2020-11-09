package com.unlam.library.integrations;

import com.unlam.library.domain.Book;
import com.unlam.library.domain.BookRental;
import com.unlam.library.domain.Client;
import com.unlam.library.domain.Employee;
import com.unlam.library.domain.Status;
import com.unlam.library.domain.StatusBookRental;
import com.unlam.library.services.BookRentalService;
import com.unlam.library.services.BookService;
import com.unlam.library.services.LibraryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookRentalServiceTest {

    private BookRentalService bookRentalService;

    @Before
    public void setup() {
        bookRentalService = BookRentalService.resetService();
    }

    @Test
    public void test_saveBookRental() {

        BookRental bookRental = new BookRental();
        BookRental result = bookRentalService.upsert(bookRental);
        Assert.assertNotNull(result.getId());
    }

    @Test
    public void test_saveBookRental_withIdExist() throws CloneNotSupportedException {

        BookRental bookRental = new BookRental();
        BookRental result = bookRentalService.upsert(bookRental);
        Assert.assertNotNull(result.getId());

        BookRental clone = (BookRental) result.clone();
        clone.setStatus(Status.DISABLED);
        BookRental result2 = bookRentalService.upsert(clone);

        Assert.assertEquals(Status.DISABLED, result2.getStatus());
    }

    @Test
    public void test_saveBookRental_withIdNotExist() {

        BookRental bookRental = new BookRental();
        bookRental.setId(1L);
        BookRental result = bookRentalService.upsert(bookRental);
        Assert.assertNull(result);
    }

    @Test
    public void test_getAllBooksBorrowed() {

        Book book = new Book();
        book.setName("book");
        Book book1 = new Book();
        book.setName("book");
        Book book2 = new Book();
        book.setName("book");
        Book book3 = new Book();
        book.setName("book");
        Book book4 = new Book();
        book.setName("book");

        BookService bookService = BookService.resetService();
        bookService.upsert(book);
        bookService.upsert(book1);
        bookService.upsert(book2);
        bookService.upsert(book3);
        bookService.upsert(book4);

        BookRental bookRental = new BookRental();
        List<Book> books = new ArrayList<>();
        books.add(book);
        bookRental.setBooks(books);
        bookRental.setStatusRental(StatusBookRental.RETURNED);
        bookRental.setStatus(Status.ENABLED);
        BookRental bookRental1 = new BookRental();
        List<Book> books1 = new ArrayList<>();
        books1.add(book1);
        books1.add(book4);
        bookRental1.setBooks(books1);
        bookRental1.setStatusRental(StatusBookRental.IN_TIME);
        bookRental1.setStatus(Status.ENABLED);
        BookRental bookRental2 = new BookRental();
        List<Book> books2 = new ArrayList<>();
        books2.add(book);
        bookRental2.setBooks(books2);
        bookRental2.setStatus(Status.DISABLED);
        bookRental2.setStatusRental(StatusBookRental.IN_TIME);
        BookRental bookRental3 = new BookRental();
        List<Book> books3 = new ArrayList<>();
        books3.add(book);
        bookRental3.setBooks(books3);
        bookRental3.setStatus(Status.ENABLED);
        bookRental3.setStatusRental(StatusBookRental.OUT_TIME);

        bookRentalService.upsert(bookRental);
        bookRentalService.upsert(bookRental1);
        bookRentalService.upsert(bookRental2);
        bookRentalService.upsert(bookRental3);

        List<Book> result = bookRentalService.getAllBooksBorrowed();

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void test_returnBooksByBookRentalId() {

        Book book = new Book();
        book.setName("book");

        BookService bookService = BookService.resetService();
        bookService.upsert(book);

        BookRental bookRental = new BookRental();
        List<Book> books = new ArrayList<>();
        books.add(book);
        bookRental.setBooks(books);
        bookRental.setStatusRental(StatusBookRental.IN_TIME);
        bookRental.setStatus(Status.ENABLED);

        BookRental result = bookRentalService.upsert(bookRental);

        bookRentalService.returnBooksByBookRentalId(result.getId());

        BookRental resultAfterUpdate  = bookRentalService.findById(result.getId()).get();
        Assert.assertEquals(StatusBookRental.RETURNED, resultAfterUpdate.getStatusRental());
    }

    @Test
    public void test_prepareOrder_ok() {
        Client client = new Client();
        client.setId(1L);
        Employee employee = new Employee();
        employee.setId(1L);
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setId(1L);
        books.add(book);

        LibraryService.resetService().addStockByBookId(1L);

        BookRental result = bookRentalService.prepareOrder(client, employee, books);

        Assert.assertNotNull(result);
        Assert.assertEquals(StatusBookRental.IN_TIME, result.getStatusRental());
        Assert.assertEquals(Status.ENABLED, result.getStatus());
    }

    @Test
    public void test_prepareOrder_invalid_client() {
        Client client = new Client();
        Employee employee = new Employee();
        List<Book> books = new ArrayList<>();
        BookRental result = bookRentalService.prepareOrder(client, employee, books);

        Assert.assertNull(result);
    }


    @Test
    public void test_prepareOrder_invalidEmployee() {
        Client client = new Client();
        client.setId(1L);
        Employee employee = new Employee();
        List<Book> books = new ArrayList<>();
        BookRental result = bookRentalService.prepareOrder(client, employee, books);

        Assert.assertNull(result);
    }


    @Test
    public void test_prepareOrder_invalidCountBooks() {
        Client client = new Client();
        client.setId(1L);
        Employee employee = new Employee();
        employee.setId(1L);
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());
        BookRental result = bookRentalService.prepareOrder(client, employee, books);

        Assert.assertNull(result);
    }

    @Test
    public void test_prepareOrder_invalidStockBooks() {
        Client client = new Client();
        client.setId(1L);
        Employee employee = new Employee();
        employee.setId(1L);
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setId(1L);
        books.add(book);

        LibraryService.resetService();

        BookRental result = bookRentalService.prepareOrder(client, employee, books);

        Assert.assertNull(result);
    }

    @Test
    public void test_prepareOrder_invalidEmptyBooks() {
        Client client = new Client();
        client.setId(1L);
        Employee employee = new Employee();
        employee.setId(1L);
        List<Book> books = new ArrayList<>();
        BookRental result = bookRentalService.prepareOrder(client, employee, books);

        Assert.assertNull(result);
    }

    @Test
    public void test_delete_object() {

        Book book = new Book();
        book.setName("book");

        BookService bookService = BookService.resetService();
        bookService.upsert(book);

        BookRental bookRental = new BookRental();
        List<Book> books = new ArrayList<>();
        books.add(book);
        bookRental.setBooks(books);
        bookRental.setStatusRental(StatusBookRental.IN_TIME);
        bookRental.setStatus(Status.ENABLED);
        BookRental result = bookRentalService.upsert(bookRental);

        Assert.assertTrue(bookRentalService.delete(result));
    }

    @Test
    public void test_delete_objectNotExist() {
        Assert.assertFalse(bookRentalService.delete(new BookRental()));
    }

    @Test
    public void test_getAllRentals() {

        List<BookRental> result = bookRentalService.findAll();
        Assert.assertEquals(0, result.size());
    }
}