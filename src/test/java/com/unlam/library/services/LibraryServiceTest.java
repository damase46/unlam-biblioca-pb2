package com.unlam.library.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.unlam.library.services.LibraryService;


public class LibraryServiceTest {

	@Before
	public void setup() {
		LibraryService libraryService= LibraryService.getInstance();
		libraryService.cleanStockBooks();
	}

	@Test
	public void addStockByBookId() {
		LibraryService libraryService= LibraryService.getInstance();
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		
		Long aux5=libraryService.addStockByBookId(0L);
		Long stock=5L;

		assertEquals(stock,aux5);
	}
	
	@Test
	public void getStockByBookId() {
		LibraryService libraryService= LibraryService.getInstance();
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(2L);
		libraryService.addStockByBookId(5L);
		libraryService.addStockByBookId(7L);		
		
		Long aux=libraryService.getStockByBookId(7L);
		Long stock=1L;
		
		assertEquals(stock,aux);
	}
	
	@Test
	public void removeStockByBookId() {
		LibraryService libraryService= LibraryService.getInstance();
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(0L);
		libraryService.addStockByBookId(2L);
		libraryService.addStockByBookId(5L);
	
		Boolean auxTrue=libraryService.removeStockByBookId(0L);
		Boolean auxFalse=libraryService.removeStockByBookId(20L);
		Long auxStock=libraryService.getStockByBookId(0L);

		Long stock=2L;
		
		assertTrue(auxTrue);
		assertFalse(auxFalse);
		assertEquals(stock,auxStock);
		
	}
	
	@Test	
	public void cleanStockBooks() {
		LibraryService libraryService= LibraryService.getInstance();
		libraryService.addStockByBookId(1L);
		libraryService.addStockByBookId(1L);
		libraryService.addStockByBookId(1L);
		libraryService.addStockByBookId(2L);
		libraryService.addStockByBookId(5L);
		libraryService.addStockByBookId(7L);
		
		Long auxStock=libraryService.getStockByBookId(0L);
		libraryService.cleanStockBooks();
		
		Long stock=0L;
		assertEquals(stock,auxStock);	
	}
	
	
}
