package com.quantum.bookstore;

import com.quantum.bookstore.model.Book;
import com.quantum.bookstore.model.EBook;
import com.quantum.bookstore.model.PaperBook;
import com.quantum.bookstore.model.ShowcaseBook;
import com.quantum.bookstore.repository.BookInventory;

/**
 * A simple demonstration of the Quantum Bookstore system.
 */
public class QuantumBookstoreDemo {
    
    public static void main(String[] args) {
        System.out.println("Quantum book store: Starting demonstration");
        
        // Create a book inventory
        BookInventory inventory = new BookInventory();
        
        // Add some books to the inventory
        System.out.println("\nQuantum book store: Adding books to inventory");
        
        // Add a paper book
        PaperBook paperBook = new PaperBook(
            "ISBN001", 
            "Java Programming", 
            2020, 
            29.99, 
            "John Doe", 
            10
        );
        inventory.addBook(paperBook);
        
        // Add an ebook
        EBook eBook = new EBook(
            "ISBN002", 
            "Web Development", 
            2021, 
            19.99, 
            "Jane Smith", 
            "PDF"
        );
        inventory.addBook(eBook);
        
        // Add a showcase book
        ShowcaseBook showcaseBook = new ShowcaseBook(
            "ISBN003", 
            "Rare Programming Techniques", 
            2010, 
            99.99, 
            "David Miller", 
            "Front Display", 
            "Demo"
        );
        inventory.addBook(showcaseBook);
        
        // Print the inventory
        System.out.println("\nQuantum book store: Current inventory");
        inventory.printInventory();
        
        // Buy a paper book
        System.out.println("\nQuantum book store: Buying a paper book");
        try {
            String isbn = "ISBN001"; // Java Programming
            int quantity = 2;
            String email = "customer@example.com";
            String address = "123 Main St, City, Country";
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
            
            // Check remaining stock
            Book book = inventory.getBook(isbn);
            if (book instanceof PaperBook) {
                PaperBook pb = (PaperBook) book;
                System.out.println("Quantum book store: Remaining stock: " + pb.getStockQuantity());
            }
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying paper book: " + e.getMessage());
        }
        
        // Buy an ebook
        System.out.println("\nQuantum book store: Buying an ebook");
        try {
            String isbn = "ISBN002"; // Web Development
            int quantity = 1;
            String email = "customer@example.com";
            String address = "Not needed for ebook";
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying ebook: " + e.getMessage());
        }
        
        // Try to buy a showcase book (should fail)
        System.out.println("\nQuantum book store: Trying to buy a showcase book (should fail)");
        try {
            String isbn = "ISBN003"; // Rare Programming Techniques
            int quantity = 1;
            String email = "customer@example.com";
            String address = "123 Main St, City, Country";
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying showcase book: " + e.getMessage());
        }
        
        System.out.println("\nQuantum book store: Demonstration completed");
    }
}