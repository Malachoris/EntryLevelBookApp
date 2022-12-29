package com.demodemoDematic.entryLevelBookStore.models;

import com.demodemoDematic.entryLevelBookStore.validators.VariableValidator;
import jakarta.persistence.Entity;

import java.util.Map;

// Bean
@Entity
public class AntiqueBook extends Book {

    private int releaseYear;

    public AntiqueBook() {
    }

    public AntiqueBook(String name, String author, String barcode, int quantity, double pricePerUnit, int releaseYear) {
        super(name, author, barcode, quantity, pricePerUnit);
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        if (!VariableValidator.isValidReleaseYear(releaseYear)) {
            throw new IllegalArgumentException("Invalid releaseYear: " + releaseYear + " must be up to 1900 year ALSO scholars generally agree that the earliest form of writing appeared almost 5,500 years so no older than 3480 BC.");
        }
        this.releaseYear = releaseYear;
    }

    // Adding additional parameters to update
    @Override
    public void update(Map<String, Object> parameters) {
        super.update(parameters);
        if (parameters.containsKey("releaseYear")) {
            setReleaseYear((int) parameters.get("releaseYear"));
        }
    }

}