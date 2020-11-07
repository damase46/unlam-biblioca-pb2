package com.unlam.library.integrations;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    	
    	List<Book> books=new ArrayList<Book>();
    	books.add(book);
    	book.setId(0l);
    	books.add(book1);
    	book.setId(1l);
    	books.add(book2);
    	book.setId(2l);
    	books.add(book3);
    	book.setId(3l);
    	
    	assertEquals(bookService.findAll().size(),books.size());
    
    }


    
    
    
    
    
}
