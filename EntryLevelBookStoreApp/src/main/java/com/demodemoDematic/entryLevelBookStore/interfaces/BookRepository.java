package com.demodemoDematic.entryLevelBookStore.interfaces;

import com.demodemoDematic.entryLevelBookStore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom field for later functionality to look books by barcodes
    Book findByBarcode(String barcode);

    List<Book> findAll();
}


