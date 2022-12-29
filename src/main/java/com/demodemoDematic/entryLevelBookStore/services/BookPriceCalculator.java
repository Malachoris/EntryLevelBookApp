package com.demodemoDematic.entryLevelBookStore.services;

import com.demodemoDematic.entryLevelBookStore.models.AntiqueBook;
import com.demodemoDematic.entryLevelBookStore.models.Book;
import com.demodemoDematic.entryLevelBookStore.models.ScienceJournal;

import java.util.Calendar;

public class BookPriceCalculator {

    // Method to calculate different type of books, not very scalable
    public static double calculateTotalPrice(Book book) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        double totalPrice = book.getQuantity() * book.getPricePerUnit();
        if (book instanceof AntiqueBook) {
            int releaseYear = ((AntiqueBook) book).getReleaseYear();
            totalPrice *= (currentYear - releaseYear) / 10.0;
        } else if (book instanceof ScienceJournal) {
            int scienceIndex = ((ScienceJournal) book).getScienceIndex();
            totalPrice *= scienceIndex;
        }
        return totalPrice;
    }
}
