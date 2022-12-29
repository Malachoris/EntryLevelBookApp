package com.demodemoDematic.entryLevelBookStore.services;

import com.demodemoDematic.entryLevelBookStore.interfaces.BookRepository;
import com.demodemoDematic.entryLevelBookStore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.demodemoDematic.entryLevelBookStore.services.BookPriceCalculator.calculateTotalPrice;

@Service
public class BookService {

    // Injecting repository
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        Book exsistingBook = bookRepository.findByBarcode(book.getBarcode());
        if (exsistingBook != null) {
            throw new DataIntegrityViolationException("Book with this barcode already exists");
        } return bookRepository.save(book);
    }

    public Book getBook(String barcode) {
        if (bookRepository.findByBarcode(barcode) != null) {
            return bookRepository.findByBarcode(barcode);
        }else throw new NullPointerException("This barcode is not created, you can add new book with this barcode");
    }

    public void updateBook(String barcode, Map<String, Object> parameters) {
        Book book = bookRepository.findByBarcode(barcode);
        book.update(parameters);
        bookRepository.save(book);
    }

    public double getTotalValue(String barcode) {
        Book book = bookRepository.findByBarcode(barcode);
        double totalValue = calculateTotalPrice(book);
        return totalValue;
    }

    // Create empty map, iterates bookRepository
    // for each book, retrieve quantity and add to map as key,
    // Key is quantity value is an empty list of strings
    // Adds the barcode to the list of strings
    // returns map eg. "5":["15915919", "15915919", "MILI0N"…]
    public Map<Integer, List<String>> getBarcodesGroupedByQuantity() {
        Map<Integer, List<String>> barcodesByQuantity = new HashMap<>();
        for (Book book : bookRepository.findAll()) {
            int quantity = book.getQuantity();
            if (!barcodesByQuantity.containsKey(quantity)) {
                barcodesByQuantity.put(quantity, new ArrayList<>());
            }
            barcodesByQuantity.get(quantity).add(book.getBarcode());
        }
        return barcodesByQuantity;
    }

    // Creates an empty map, iterates over the keys in the input map
    // For each key (quantity), the method retrieves the list of barcodes
    // And sorts descending order (expensive barcodes at top)
    public Map<Integer, List<String>> getBarcodesSortedByTotalPrice(Map<Integer, List<String>> barcodesByQuantity) {
        Map<Integer, List<String>> sortedBarcodesByQuantity = new HashMap<>();
        for (int quantity : barcodesByQuantity.keySet()) {
        List<String> barcodes = barcodesByQuantity.get(quantity);
        // Sort the barcodes by total price makaroni
        barcodes.sort((barcode1, barcode2) -> Double.compare(calculateTotalPrice(bookRepository.findByBarcode(barcode2)), calculateTotalPrice(bookRepository.findByBarcode(barcode1))));
        sortedBarcodesByQuantity.put(quantity, barcodes);
        }
        return sortedBarcodesByQuantity;
    }
}

