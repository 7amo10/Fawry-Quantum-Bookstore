package com.quantum.bookstore.service;

/**
 * Interface for shipping service to deliver paper books to customers.
 */
public interface ShippingService {
    
    /**
     * Ship a paper book to a customer.
     */
    String shipBook(String isbn, String title, String address);
}