package com.unlam.library.integrations;

import static org.junit.Assert.assertEquals;

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
    	BookService bookService=BookService.getInstance();
    
    	bookService.upsert(book);
    	Book addBook=bookService.upsert(book1);
    	
    	assertEquals(book1,addBook);
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
