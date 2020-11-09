package com.unlam.library.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LibraryService {

    private static LibraryService libraryService;
    private Map<Long, Long> stockBooks;

    private LibraryService() {
        stockBooks = new HashMap<>();
    }

    public Long getStockByBookId(Long bookId) {
        return Optional.ofNullable(stockBooks.get(bookId)).orElse(0L);
    }

    public Long addStockByBookId(Long bookId) {
        Long finalStock = 1L;
        if(stockBooks.containsKey(bookId)) {
            finalStock =stockBooks.get(bookId) + 1;
        }
        stockBooks.put(bookId, finalStock);
        return finalStock;
    }

    public Boolean removeStockByBookId(Long bookId) {
        Long finalStock = 1L;
    	if(stockBooks.containsKey(bookId)) {
            finalStock =stockBooks.get(bookId) -1;
            if(finalStock <0L) {
            	finalStock = 0L;
            }
            stockBooks.put(bookId, finalStock);
            return true;
    	}
    	return false;
    }
    
    public void cleanStockBooks() {
    	stockBooks = new HashMap<>();
    }
    
    public static LibraryService getInstance() {
        return libraryService = libraryService == null ? new LibraryService() : libraryService;
    }
}

