package com.unlam.library.services;

import static org.junit.Assert.*;

import java.util.Optional;

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
		Author author=new Author();
		
		Author aux=authorService.upsert(author);
		Long add=0L;
	
		assertEquals(add,aux.getId());
	}
	
	@Test
	public void findById() {
		AuthorService authorService=AuthorService.getInstance();
		Author author=new Author();
		
		Author aux=authorService.upsert(author);
		System.out.println(aux.getId());
		
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

	}
	@Test
	public void findByIdentification() {
		AuthorService authorService=AuthorService.getInstance();

	}
}
