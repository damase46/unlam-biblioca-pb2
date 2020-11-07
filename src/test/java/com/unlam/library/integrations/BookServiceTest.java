package com.unlam.library.integrations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

import com.unlam.library.domain.Author;
import com.unlam.library.domain.Book;
import com.unlam.library.domain.Editorial;
import com.unlam.library.domain.Gender;
import com.unlam.library.services.BookService;

public class BookServiceTest {

    @Test
    public void save_book() {
    	Author author=new Author();
    	Editorial editorial=new Editorial();
		Date publicationDate=new Date(); 	
    	Book book=  new Book("Eragon 1", author, Gender.FANTASY,publicationDate, editorial);
    	Book book1=  new Book("Eragon 2", author, Gender.FANTASY,publicationDate, editorial);
    	Book bookUpdate=  new Book("Eragon 3", author, Gender.FANTASY,publicationDate, editorial);
    	
    	BookService bookService=BookService.getInstance();
    
    	Book addBook=bookService.upsert(book);
    	Book addBook1=bookService.upsert(book1);
    	Book addBookNull=bookService.upsert(null);
    	bookService.upsert(bookUpdate);
    	bookUpdate.setId(0l);
    	
    	assertEquals(book,addBook);    	
    	assertEquals(book1,addBook1);
    	assertNull(addBookNull);
    	assertEquals(bookUpdate.getName(),bookService.findById(0l).get().getName());
    }

    @Test
    public void save_book_with_autor_exist() {
        // TODO implement
    }

    @Test
    public void delete_book() {
        // TODO implement
    }

    @Test
    public void update_book() {
        // TODO implement
    }

    @Test
    public void findAll_books() {
        // TODO implement
    }
}
