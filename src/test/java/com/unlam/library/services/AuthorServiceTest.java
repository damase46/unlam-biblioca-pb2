package com.unlam.library.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.unlam.library.domain.Author;
import com.unlam.library.domain.Person;

public class AuthorServiceTest {
	@Before
	public void setup() {
		AuthorService authorService=AuthorService.getInstance();
		authorService.cleanAuthor();
	}
	@Test
	public void upSert() {
		AuthorService authorService=AuthorService.getInstance();
		Person person=new Person();
		PersonService personService=PersonService.getInstance();
		personService.upsert(person);
		Author author=new Author();
		
		Author aux=authorService.upsert(author);
		Long add=1L;
		
		assertEquals(add,aux.getId());
	}
	
	@Test
	public void findById() {
		AuthorService authorService=AuthorService.getInstance();
		Person person=new Person();
		PersonService personService=PersonService.getInstance();
		personService.upsert(person);
		Author author=new Author();
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

	}
	@Test
	public void findByIdentification() {
		AuthorService authorService=AuthorService.getInstance();

	}
}
