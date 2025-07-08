#!/bin/bash

# ANSI color codes for better readability
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}=== Quantum Bookstore Runner ===${NC}"
echo

# Create the output directories for compiled classes
echo -e "${YELLOW}Creating output directories...${NC}"
mkdir -p out/main out/test

# Compile the main source files
echo -e "${YELLOW}Compiling main source files...${NC}"
javac -d out/main src/main/java/com/quantum/bookstore/model/*.java \
                  src/main/java/com/quantum/bookstore/service/*.java \
                  src/main/java/com/quantum/bookstore/repository/*.java \
                  src/main/java/com/quantum/bookstore/exception/*.java \
                  src/main/java/com/quantum/bookstore/*.java

if [ $? -ne 0 ]; then
    echo -e "${RED}Error compiling main source files. Exiting.${NC}"
    exit 1
fi

# Compile the test source files
echo -e "${YELLOW}Compiling test source files...${NC}"
javac -cp out/main -d out/test src/test/java/com/quantum/bookstore/*.java

if [ $? -ne 0 ]; then
    echo -e "${RED}Error compiling test source files. Exiting.${NC}"
    exit 1
fi

echo -e "${GREEN}Compilation successful!${NC}"
echo

# Menu for selecting which program to run
echo -e "${BLUE}Please select an option:${NC}"
echo "1) Run Demo (Recommended for first-time users)"
echo "2) Run Full Test Suite"
echo "3) Exit"
read -p "Enter your choice (1-3): " choice

case $choice in
    1)
        echo -e "${YELLOW}Running Demo...${NC}"
        echo "----------------------------------------"
        java -cp out/main com.quantum.bookstore.QuantumBookstoreDemo
        echo "----------------------------------------"
        echo -e "${GREEN}Demo completed.${NC}"
        ;;
    2)
        echo -e "${YELLOW}Running Full Test Suite...${NC}"
        echo "----------------------------------------"
        java -cp out/main:out/test test.java.com.quantum.bookstore.QuantumBookstoreFullTest
        echo "----------------------------------------"
        echo -e "${GREEN}Test suite completed.${NC}"
        ;;
    3)
        echo -e "${BLUE}Exiting.${NC}"
        exit 0
        ;;
    *)
        echo -e "${RED}Invalid choice. Exiting.${NC}"
        exit 1
        ;;
esac

echo
echo -e "${BLUE}Thank you for using Quantum Bookstore!${NC}"