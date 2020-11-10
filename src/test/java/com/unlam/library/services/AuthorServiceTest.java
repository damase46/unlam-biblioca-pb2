package com.unlam.library.services;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.unlam.library.domain.Author;

public class AuthorServiceTest {
	@Before
	public void setup() {
		AuthorService authorService=AuthorService.getInstance();
		authorService.cleanAuthor();
		PersonService personService=PersonService.getInstance();
		personService.resetService();
	}
	
	@Test
	public void upSert() {
		AuthorService authorService=AuthorService.getInstance();
		Author author=new Author();
		
		Author aux=authorService.upsert(author);
		Long add=0L;
	
		assertEquals(add,aux.getId());
	}
	
	@Test
	public void findById() {
		AuthorService authorService=AuthorService.getInstance();
		Author author=new Author();
		Author author1=new Author();
		authorService.upsert(author1);
		Author aux=authorService.upsert(author);
		
		assertEquals(Optional.empty(),authorService.findById(15l));
		assertEquals(aux.getId(),authorService.findById(1l).get().getId());

	}
	
	@Test
	public void delete() {
		AuthorService authorService=AuthorService.getInstance();

	}
	@Test
	public void deleteBy() {
		AuthorService authorService=AuthorService.getInstance();

	}
	@Test
	public void findAll() {
		AuthorService authorService=AuthorService.getInstance();
		Author author=new Author();
		authorService.upsert(author);
		authorService.upsert(author);
		authorService.upsert(author);
		authorService.upsert(author);
		
		assertEquals(4l,authorService.findAll().size());
	}
	@Test
	public void findByIdentification() {
		AuthorService authorService=AuthorService.getInstance();

	}
}
