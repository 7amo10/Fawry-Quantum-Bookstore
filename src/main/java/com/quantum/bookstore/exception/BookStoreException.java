package com.quantum.bookstore.exception;

/**
 * Custom exception class for book store related errors.
 */
public class BookStoreException extends Exception {
    
    public BookStoreException(String message) {
        super("Quantum book store: " + message);
    }
    
    public BookStoreException(String message, Throwable cause) {
        super("Quantum book store: " + message, cause);
    }
    
    /**
     * Create an exception for when a book is not found.
     */
    public static BookStoreException bookNotFound(String isbn) {
        return new BookStoreException("Book with ISBN " + isbn + " not found");
    }
    
    /**
     * Create an exception for when a book is not available for purchase.
     */
    public static BookStoreException bookNotAvailable(String title) {
        return new BookStoreException("Book '" + title + "' is not available for purchase");
    }
    
    /**
     * Create an exception for when there is insufficient stock.
     */
    public static BookStoreException insufficientStock(String title, int quantity, int stock) {
        return new BookStoreException("Insufficient stock for book '" + title + 
                "'. Requested: " + quantity + ", Available: " + stock);
    }
}