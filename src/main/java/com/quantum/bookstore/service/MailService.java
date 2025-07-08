package com.quantum.bookstore.service;

/**
 * Interface for mail service to deliver ebooks to customers.
 */
public interface MailService {
    
    /**
     * Send an ebook to a customer via email.
     */
    String sendEbook(String isbn, String title, String fileType, String email);
}