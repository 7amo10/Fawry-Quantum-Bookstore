package com.quantum.bookstore.model;

/**
 * Represents a physical paper book in the Quantum Bookstore.
 */
public class PaperBook extends Book {
    private int stockQuantity;

    /**
     * Constructor for creating a paper book.
     */
    public PaperBook(String isbn, String title, int publishYear, double price, String authorName, int stockQuantity) {
        super(isbn, title, publishYear, price, authorName);
        this.stockQuantity = stockQuantity;
    }

    /**
     * Check if the paper book is available for purchase.
     */
    @Override
    public boolean isAvailableForPurchase() {
        return stockQuantity > 0;
    }

    /**
     * Process the purchase of a paper book.
     */
    @Override
    public double purchase(int quantity, String email, String address) throws Exception {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantum book store: Quantity must be greater than zero");
        }

        if (stockQuantity < quantity) {
            throw new Exception("Quantum book store: Not enough stock available. Current stock: " + stockQuantity);
        }

        stockQuantity -= quantity;
        double totalAmount = getPrice() * quantity;

        // Send to shipping service (no implementation required as per requirements)
        System.out.println("Quantum book store: Sending paper book '" + getTitle() + "' to shipping service. Address: " + address);

        return totalAmount;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Add stock to the paper book.
     */
    public void addStock(int quantity) {
        if (quantity > 0) {
            this.stockQuantity += quantity;
        }
    }

    @Override
    public String toString() {
        return "PaperBook{" +
                "isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", publishYear=" + getPublishYear() +
                ", price=" + getPrice() +
                ", authorName='" + getAuthorName() + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}