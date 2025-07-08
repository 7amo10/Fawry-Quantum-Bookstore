package com.quantum.bookstore.repository;

import com.quantum.bookstore.exception.BookStoreException;
import com.quantum.bookstore.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages the inventory of books in the Quantum Bookstore.
 */
public class BookInventory {
    private Map<String, Book> books;

    public BookInventory() {
        this.books = new HashMap<>();
    }

    /**
     * Add a book to the inventory.
     */
    public boolean addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            System.out.println("Quantum book store: Cannot add book with null or empty ISBN");
            return false;
        }

        if (books.containsKey(book.getIsbn())) {
            System.out.println("Quantum book store: Book with ISBN " + book.getIsbn() + " already exists in inventory");
            return false;
        }

        books.put(book.getIsbn(), book);
        System.out.println("Quantum book store: Added book: " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        return true;
    }

    /**
     * Remove a book from the inventory by ISBN.
     */
    public Book removeBook(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            System.out.println("Quantum book store: Cannot remove book with null or empty ISBN");
            return null;
        }

        Book removedBook = books.remove(isbn);
        if (removedBook != null) {
            System.out.println("Quantum book store: Removed book: " + removedBook.getTitle() + " (ISBN: " + isbn + ")");
        } else {
            System.out.println("Quantum book store: No book found with ISBN: " + isbn);
        }
        return removedBook;
    }

    /**
     * Remove and return outdated books.
     */
    public List<Book> removeOutdatedBooks(int years) {
        if (years <= 0) {
            System.out.println("Quantum book store: Years must be greater than zero");
            return new ArrayList<>();
        }

        List<Book> outdatedBooks = books.values().stream()
                .filter(book -> book.getAge() > years)
                .collect(Collectors.toList());

        for (Book book : outdatedBooks) {
            books.remove(book.getIsbn());
            System.out.println("Quantum book store: Removed outdated book: " + book.getTitle() + 
                    " (ISBN: " + book.getIsbn() + ", Age: " + book.getAge() + " years)");
        }

        return outdatedBooks;
    }

    /**
     * Buy a book by ISBN.
     */
    public double buyBook(String isbn, int quantity, String email, String address) throws Exception {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("Quantum book store: ISBN cannot be null or empty");
        }

        Book book = books.get(isbn);
        if (book == null) {
            throw BookStoreException.bookNotFound(isbn);
        }

        if (!book.isAvailableForPurchase()) {
            throw BookStoreException.bookNotAvailable(book.getTitle());
        }

        return book.purchase(quantity, email, address);
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public int getBookCount() {
        return books.size();
    }

    public void printInventory() {
        System.out.println("Quantum book store: Inventory - " + books.size() + " books");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }
}