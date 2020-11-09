package com.unlam.library.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

	}
	
	@Test
	public void removeStockByBookId() {
		LibraryService libraryService= LibraryService.getInstance();

	}	
}
