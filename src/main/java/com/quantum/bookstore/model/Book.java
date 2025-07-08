package com.quantum.bookstore.model;

/**
 * Abstract base class for all types of books in the Quantum Bookstore.
 */
public abstract class Book {
    private String isbn;
    private String title;
    private int publishYear;
    private double price;
    private String authorName;

    /**
     * Constructor for creating a book with all required details.
     */
    public Book(String isbn, String title, int publishYear, double price, String authorName) {
        this.isbn = isbn;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
        this.authorName = authorName;
    }

    /**
     * Check if a book is available for purchase.
     * @return true if available for purchase
     */
    public abstract boolean isAvailableForPurchase();

    /**
     * Process the purchase of a book.
     * @return The total amount paid
     * @throws Exception if purchase fails
     */
    public abstract double purchase(int quantity, String email, String address) throws Exception;

    /**
     * Calculate the age of the book in years.
     */
    public int getAge() {
        return java.time.Year.now().getValue() - publishYear;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", price=" + price +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}