package test.java.com.quantum.bookstore;

import com.quantum.bookstore.model.Book;
import com.quantum.bookstore.model.EBook;
import com.quantum.bookstore.model.PaperBook;
import com.quantum.bookstore.model.ShowcaseBook;
import com.quantum.bookstore.repository.BookInventory;

import java.util.List;

/**
 * Test class for the Quantum Bookstore.
 */
public class QuantumBookstoreFullTest {
    
    public static void main(String[] args) {
        System.out.println("Quantum book store: Starting full test");
        
        // Create a book inventory
        BookInventory inventory = new BookInventory();
        
        // Test adding books
        testAddBooks(inventory);
        
        // Test removing outdated books
        testRemoveOutdatedBooks(inventory);
        
        // Test buying books
        testBuyBooks(inventory);
        
        System.out.println("Quantum book store: Full test completed");
    }
    
    /**
     * Test adding different types of books to the inventory.
     */
    private static void testAddBooks(BookInventory inventory) {
        System.out.println("\nQuantum book store: Testing adding books");
        
        // Add paper books
        PaperBook paperBook1 = new PaperBook("ISBN001", "Java Programming", 2020, 29.99, "John Doe", 10);
        PaperBook paperBook2 = new PaperBook("ISBN002", "Python Basics", 2018, 24.99, "Jane Smith", 5);
        PaperBook paperBook3 = new PaperBook("ISBN003", "C++ Advanced", 2015, 34.99, "Bob Johnson", 3);
        
        // Add ebooks
        EBook eBook1 = new EBook("ISBN004", "Web Development", 2021, 19.99, "Alice Brown", "PDF");
        EBook eBook2 = new EBook("ISBN005", "Mobile App Development", 2019, 22.99, "Charlie Wilson", "EPUB");
        
        // Add showcase books
        ShowcaseBook showcaseBook1 = new ShowcaseBook("ISBN006", "Rare Programming Techniques", 2010, 
                99.99, "David Miller", "Front Display", "Demo");
        ShowcaseBook showcaseBook2 = new ShowcaseBook("ISBN007", "Historical Computing", 2005, 
                149.99, "Eva Davis", "Special Collection", "Display");
        
        // Add books to inventory
        inventory.addBook(paperBook1);
        inventory.addBook(paperBook2);
        inventory.addBook(paperBook3);
        inventory.addBook(eBook1);
        inventory.addBook(eBook2);
        inventory.addBook(showcaseBook1);
        inventory.addBook(showcaseBook2);
        
        // Try to add a book with duplicate ISBN
        PaperBook duplicateBook = new PaperBook("ISBN001", "Duplicate Book", 2022, 39.99, "Duplicate Author", 20);
        boolean result = inventory.addBook(duplicateBook);
        System.out.println("Quantum book store: Adding duplicate book result: " + result);
        
        // Print inventory
        System.out.println("\nQuantum book store: Current inventory after adding books:");
        inventory.printInventory();
    }
    
    /**
     * Test removing outdated books from the inventory.
     */
    private static void testRemoveOutdatedBooks(BookInventory inventory) {
        System.out.println("\nQuantum book store: Testing removing outdated books");
        
        int currentYear = java.time.Year.now().getValue();
        
        PaperBook oldBook1 = new PaperBook("ISBN008", "Old Programming Book", currentYear - 10, 
                9.99, "Old Author 1", 2);
        PaperBook oldBook2 = new PaperBook("ISBN009", "Very Old Programming Book", currentYear - 15, 
                4.99, "Old Author 2", 1);
        EBook oldBook3 = new EBook("ISBN010", "Old Digital Book", currentYear - 8, 
                7.99, "Old Author 3", "PDF");
        
        inventory.addBook(oldBook1);
        inventory.addBook(oldBook2);
        inventory.addBook(oldBook3);
        
        System.out.println("\nQuantum book store: Inventory before removing outdated books:");
        inventory.printInventory();
        
        System.out.println("\nQuantum book store: Removing books older than 7 years:");
        List<Book> outdatedBooks = inventory.removeOutdatedBooks(7);
        
        System.out.println("Quantum book store: Removed " + outdatedBooks.size() + " outdated books:");
        for (Book book : outdatedBooks) {
            System.out.println("  - " + book.getTitle() + " (ISBN: " + book.getIsbn() + 
                    ", Published: " + book.getPublishYear() + ")");
        }
        
        System.out.println("\nQuantum book store: Inventory after removing outdated books:");
        inventory.printInventory();
    }
    
    /**
     * Test buying books from the inventory.
     */
    private static void testBuyBooks(BookInventory inventory) {
        System.out.println("\nQuantum book store: Testing buying books");
        
        try {
            String isbn = "ISBN001"; // Java Programming
            int quantity = 2;
            String email = "customer@example.com";
            String address = "123 Main St, City, Country";
            
            System.out.println("Quantum book store: Buying paper book with ISBN " + isbn + 
                    ", Quantity: " + quantity);
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
            
            // Check remaining stock
            Book book = inventory.getBook(isbn);
            if (book instanceof PaperBook) {
                PaperBook paperBook = (PaperBook) book;
                System.out.println("Quantum book store: Remaining stock: " + paperBook.getStockQuantity());
            }
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying paper book: " + e.getMessage());
        }
        
        try {
            String isbn = "ISBN004"; // Web Development
            int quantity = 1;
            String email = "customer@example.com";
            String address = "Not needed for ebook";
            
            System.out.println("\nQuantum book store: Buying ebook with ISBN " + isbn + 
                    ", Quantity: " + quantity);
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying ebook: " + e.getMessage());
        }
        
        try {
            String isbn = "ISBN006"; // Rare Programming Techniques
            int quantity = 1;
            String email = "customer@example.com";
            String address = "123 Main St, City, Country";
            
            System.out.println("\nQuantum book store: Trying to buy showcase book with ISBN " + isbn + 
                    ", Quantity: " + quantity);
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying showcase book: " + e.getMessage());
        }
        
        try {
            String isbn = "ISBN002"; // Python Basics
            int quantity = 10; // Only 5 in stock
            String email = "customer@example.com";
            String address = "123 Main St, City, Country";
            
            System.out.println("\nQuantum book store: Trying to buy paper book with ISBN " + isbn + 
                    ", Quantity: " + quantity + " (exceeds stock)");
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying paper book: " + e.getMessage());
        }
        
        try {
            String isbn = "ISBN999"; // Non-existent
            int quantity = 1;
            String email = "customer@example.com";
            String address = "123 Main St, City, Country";
            
            System.out.println("\nQuantum book store: Trying to buy non-existent book with ISBN " + isbn);
            
            double amount = inventory.buyBook(isbn, quantity, email, address);
            System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
        } catch (Exception e) {
            System.out.println("Quantum book store: Error buying non-existent book: " + e.getMessage());
        }
    }
}