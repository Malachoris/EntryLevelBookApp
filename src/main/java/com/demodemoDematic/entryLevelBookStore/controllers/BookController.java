package com.demodemoDematic.entryLevelBookStore.controllers;

import com.demodemoDematic.entryLevelBookStore.models.Book;
import com.demodemoDematic.entryLevelBookStore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book putBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/{barcode}")
    public Book getBook(@PathVariable String barcode) {
        return bookService.getBook(barcode);
    }

    @PutMapping("/{barcode}")
    public Book updateBook(@PathVariable String barcode, @RequestBody Map<String, Object> parameters) {
        bookService.updateBook(barcode, parameters);
        return bookService.getBook(barcode);
    }

    @GetMapping("/{barcode}/total-price")
    public double getTotalValue(@PathVariable String barcode) {
        return bookService.getTotalValue(barcode);
    }

    @GetMapping("/barcodes/grouped-by-quantity")
    public Map<Integer, List<String>> getBarcodesGroupedByQuantitySortedByTotalPrice() {
        return bookService.getBarcodesGroupedByQuantity();
    }

    @GetMapping("/barcodes/grouped-by-quantity/sorted-by-price")
    public Map<Integer, List<String>> barcodesSortedByTotalPrice(Map<Integer, List<String>> barcodesByQuantity) {
        return bookService.getBarcodesSortedByTotalPrice(bookService.getBarcodesGroupedByQuantity());
    }
}
