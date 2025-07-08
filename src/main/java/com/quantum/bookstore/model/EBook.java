package com.quantum.bookstore.model;

/**
 * Represents an electronic book in the Quantum Bookstore.
 */
public class EBook extends Book {
    private String fileType;

    /**
     * Constructor for creating an ebook.
     */
    public EBook(String isbn, String title, int publishYear, double price, String authorName, String fileType) {
        super(isbn, title, publishYear, price, authorName);
        this.fileType = fileType;
    }

    /**
     * Check if the ebook is available for purchase.
     */
    @Override
    public boolean isAvailableForPurchase() {
        return true; // EBooks are always available
    }

    /**
     * Process the purchase of an ebook.
     */
    @Override
    public double purchase(int quantity, String email, String address) throws Exception {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantum book store: Quantity must be greater than zero");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Quantum book store: Email is required for ebook delivery");
        }

        // Charge for the requested quantity to maintain consistency
        double totalAmount = getPrice() * quantity;

        // Send to mail service (no implementation required as per requirements)
        System.out.println("Quantum book store: Sending ebook '" + getTitle() + "' to mail service. Email: " + email + ", File type: " + fileType);

        return totalAmount;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", publishYear=" + getPublishYear() +
                ", price=" + getPrice() +
                ", authorName='" + getAuthorName() + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}