package com.quantum.bookstore.model;

/**
 * Represents a showcase or demo book in the Quantum Bookstore.
 */
public class ShowcaseBook extends Book {
    private String displayLocation;
    private String purpose;

    /**
     * Constructor for creating a showcase book.
     */
    public ShowcaseBook(String isbn, String title, int publishYear, double price, String authorName, 
                        String displayLocation, String purpose) {
        super(isbn, title, publishYear, price, authorName);
        this.displayLocation = displayLocation;
        this.purpose = purpose;
    }

    /**
     * Check if the showcase book is available for purchase.
     */
    @Override
    public boolean isAvailableForPurchase() {
        return false; // Showcase books are not for sale
    }

    /**
     * Process the purchase of a showcase book.
     * Always throws an exception as showcase books cannot be purchased.
     */
    @Override
    public double purchase(int quantity, String email, String address) throws Exception {
        throw new UnsupportedOperationException("Quantum book store: Showcase books are not for sale");
    }

    public String getDisplayLocation() {
        return displayLocation;
    }

    public void setDisplayLocation(String displayLocation) {
        this.displayLocation = displayLocation;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "ShowcaseBook{" +
                "isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", publishYear=" + getPublishYear() +
                ", price=" + getPrice() +
                ", authorName='" + getAuthorName() + '\'' +
                ", displayLocation='" + displayLocation + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}