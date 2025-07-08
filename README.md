# Quantum Bookstore

A Java implementation of an online bookstore system that demonstrates advanced object-oriented programming principles, clean architecture, and industry best practices.

## Project Overview

Quantum Bookstore is a comprehensive book inventory management system that allows for the management of different types of books:
- **Paper Books**: Physical books with stock quantities that can be shipped to customers
- **E-Books**: Digital books with specific file types that can be sent via email
- **Showcase/Demo Books**: Books that are not for sale but are used for display or demonstration purposes

The system provides functionality for adding books to inventory, removing outdated books, and purchasing books with appropriate delivery methods based on the book type.


## Architecture

The project follows a clean architecture with proper separation of concerns and implements several design patterns and SOLID principles:

### Core Components

1. **Book (Abstract Class)**: Base class for all book types with common properties and behaviors
   - Implements the Template Method pattern for the purchase process
   - Uses abstraction to define common behaviors while allowing specific implementations

2. **Book Type Implementations**:
   - **PaperBook**: Implements physical book behavior with stock management
   - **EBook**: Implements digital book behavior with file type and email delivery
   - **ShowcaseBook**: Implements showcase book behavior (not for sale)

3. **BookInventory**: Manages the collection of books and provides operations for adding, removing, and buying books
   - Implements the Repository pattern for book storage and retrieval

4. **Service Interfaces**:
   - **ShippingService**: Interface for shipping paper books
   - **MailService**: Interface for sending ebooks via email

5. **Exception Handling**:
   - **BookStoreException**: Custom exception class for book-related errors

6. **Testing**:
   - **QuantumBookstoreFullTest**: Comprehensive test class for testing all functionality


## Installation Instructions

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, etc.) or command-line tools

### Setup
1. Clone the repository or download the source code
2. Import the project into your IDE as a Java project
3. Ensure the source folders are properly configured:
   - `src/main/java` as a source folder
   - `src/test/java` as a test source folder

## Running the Application

The project includes two main classes that you can run. To compile and run the application from the command line, follow these instructions:

### Using the Automated Run Script (Recommended)

For convenience, an automated run script is provided that handles compilation and execution in one step:

```bash
# Make the script executable (only needed once)
chmod +x run.sh

# Run the script
./run.sh
```

The script will:
1. Create the necessary output directories
2. Compile all the Java files
3. Present a menu to choose between:
   - Running the Demo (recommended for first-time users)
   - Running the Full Test Suite
   - Exiting the program

This is the easiest way to run the application, especially for first-time users.

### Manual Compilation and Execution (Alternative)

If you prefer to compile and run the application manually, follow these steps:

#### 1. Compile the Project

First, compile all the Java files from the root directory of the project:

```bash
# Create the output directories for compiled classes
mkdir -p out/main out/test

# Compile the main source files
javac -d out/main src/main/java/com/quantum/bookstore/model/*.java src/main/java/com/quantum/bookstore/service/*.java src/main/java/com/quantum/bookstore/repository/*.java src/main/java/com/quantum/bookstore/exception/*.java src/main/java/com/quantum/bookstore/*.java

# Compile the test source files
javac -cp out/main -d out/test src/test/java/com/quantum/bookstore/*.java
```

#### 2. Running the Demo (Recommended for First-Time Users)

The `QuantumBookstoreDemo` class provides a simple demonstration of the basic functionality:

```bash
java -cp out/main com.quantum.bookstore.QuantumBookstoreDemo
```

This will:
- Create a book inventory
- Add sample books (paper book, ebook, showcase book)
- Demonstrate buying a paper book
- Demonstrate buying an ebook
- Demonstrate attempting to buy a showcase book (which will fail)

#### 3. Running the Full Test Suite

The `QuantumBookstoreFullTest` class provides a comprehensive test of all functionality:

```bash
java -cp out/main:out/test com.quantum.bookstore.QuantumBookstoreFullTest
```

This will run a series of tests that demonstrate:
- Adding different types of books to the inventory
- Removing outdated books
- Buying books (paper books and ebooks)
- Handling error cases (insufficient stock, non-existent books, etc.)

## Project Management

### Cleaning the Project

The project includes a `.gitignore` file that prevents compiled files and other unnecessary files from being included in version control. If you want to clean the project manually (remove compiled files), you can use the following commands:

```bash
# Remove any compiled class files
find . -name "*.class" -type f -delete

# Remove the out directory if it exists
rm -rf out/
```

### Key Files for Project Management

- **run.sh**: Automated script for compiling and running the application
- **.gitignore**: Configuration file for Git to exclude compiled files and other unnecessary files from version control
- **README.md**: This documentation file with project information and instructions

## Usage Examples

### Using the Bookstore in Your Own Code

```java
import com.quantum.bookstore.model.Book;
import com.quantum.bookstore.model.PaperBook;
import com.quantum.bookstore.model.EBook;
import com.quantum.bookstore.repository.BookInventory;

// Create a book inventory
BookInventory inventory = new BookInventory();

// Add books to the inventory
PaperBook paperBook = new PaperBook("ISBN001", "Java Programming", 2020, 29.99, "John Doe", 10);
EBook eBook = new EBook("ISBN002", "Web Development", 2021, 19.99, "Jane Smith", "PDF");
inventory.addBook(paperBook);
inventory.addBook(eBook);

// Buy a book
try {
    double amount = inventory.buyBook("ISBN001", 2, "customer@example.com", "123 Main St, City, Country");
    System.out.println("Quantum book store: Purchase successful. Amount paid: $" + amount);
} catch (Exception e) {
    System.out.println("Quantum book store: Error buying book: " + e.getMessage());
}

// Remove outdated books
List<Book> outdatedBooks = inventory.removeOutdatedBooks(5);
```

