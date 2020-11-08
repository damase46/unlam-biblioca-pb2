package com.unlam.library.services;

import com.unlam.library.domain.Author;
import com.unlam.library.domain.Book;
import com.unlam.library.domain.Editorial;
import com.unlam.library.domain.Gender;
import com.unlam.library.services.BookService;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BookServiceTest {

	@Before
	public void setup() {
		BookService bookService = BookService.getInstance();
		bookService.cleanBooks();
	}

    @Test
    public void save_book() {
    	Author author=new Author();
    	Editorial editorial=new Editorial();
		Date publicationDate=new Date(); 	
    	Book book=  new Book("save_book 1", author, Gender.FANTASY,publicationDate, editorial);
    	Book book1=  new Book("save_book 2", author, Gender.FANTASY,publicationDate, editorial);

    	BookService bookService=BookService.getInstance();
    
    	Book addBook=bookService.upsert(book);
    	Book addBook1=bookService.upsert(book1);
    	Book addBookNull=bookService.upsert(null);
    	
    	assertEquals(book,addBook);    	
    	assertEquals(book1,addBook1);
    	assertNull(addBookNull);
    }

    @Test
    public void save_book_with_autor_exist() {
    	Author author=new Author();
    	Editorial editorial=new Editorial();
		Date publicationDate=new Date(); 	
    	Book book=  new Book("save_book_with_autor_exist 1", author, Gender.FANTASY,publicationDate, editorial);
    	Book book1=  new Book("save_book_with_autor_exist 2", author, Gender.FANTASY,publicationDate, editorial);

    	BookService bookService=BookService.getInstance();
    
    	Book addBook=bookService.upsert(book);
    	Book addBook1=bookService.upsert(book1);

    	
    	assertEquals(book,addBook);    	
    	assertEquals(book1,addBook1);
    }

    @Test
    public void delete_book() {
       	Author author=new Author();
    	Editorial editorial=new Editorial();
		Date publicationDate=new Date(); 	
    	Book book=  new Book("delete_book 1", author, Gender.FANTASY,publicationDate, editorial);
    	Book book1=  new Book("delete_book 2", author, Gender.FANTASY,publicationDate, editorial);
    	Book book2=  new Book("delete_book 3", author, Gender.FANTASY,publicationDate, editorial);   	
    	BookService bookService=BookService.getInstance();
    
    	bookService.upsert(book);
    	bookService.upsert(book1);
    	bookService.upsert(book2);
    	
    	Boolean deleteBook=bookService.delete(book);
    	Boolean deleteBookId=bookService.deleteBy(2l);
    	Boolean deleteBookNotId=bookService.deleteBy(2l);   
    	Boolean deleteBookNull=bookService.delete(null);
    	
    	bookService.findAll();
    	
    	assertTrue(deleteBook);
    	assertTrue(deleteBookId);
    	assertFalse(deleteBookNotId);
    	assertFalse(deleteBookNull);
    }

    @Test
    public void update_book() {
       	Author author=new Author();
    	Editorial editorial=new Editorial();
		Date publicationDate=new Date(); 	
    	Book book=  new Book("update_book 1", author, Gender.FANTASY,publicationDate, editorial);
    	Book book1=  new Book("update_book 2", author, Gender.FANTASY,publicationDate, editorial);
    	Book bookUpdate=  new Book("update_book 3", author, Gender.FANTASY,publicationDate, editorial);   	
    	BookService bookService=BookService.getInstance();
    
    	bookService.upsert(book);
    	bookService.upsert(book1);
    	bookService.upsert(bookUpdate);
    	bookUpdate.setId(0l);
    	
    	assertEquals(bookUpdate.getName(),bookService.findById(0l).get().getName());
    }

    @Test
    public void findAll_books() {
       	Author author=new Author();
    	Editorial editorial=new Editorial();
		Date publicationDate=new Date(); 	
    	Book book=  new Book("findAll_books 1", author, Gender.FANTASY,publicationDate, editorial);
    	Book book1=  new Book("findAll_books", author, Gender.FANTASY,publicationDate, editorial);
    	Book book2=  new Book("findAll_books", author, Gender.FANTASY,publicationDate, editorial);   	
    	Book book3=  new Book("findAll_books", author, Gender.FANTASY,publicationDate, editorial);   	
    	BookService bookService=BookService.getInstance();
    
    	bookService.upsert(book);
    	bookService.upsert(book1);   
    	bookService.upsert(book2);
    	bookService.upsert(book3);
    	
    	assertEquals(bookService.findAll().size(),4);
    
    }
}
