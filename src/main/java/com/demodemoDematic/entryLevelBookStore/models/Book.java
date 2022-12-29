package com.demodemoDematic.entryLevelBookStore.models;

import com.demodemoDematic.entryLevelBookStore.validators.VariableValidator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Map;

// Creates bean for SpringBoot, makes it main table, tells that we're going to have 1 table per class
// Very slick Json solution for adding multiple types of books
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AntiqueBook.class, name = "Antique"),
        @JsonSubTypes.Type(value = ScienceJournal.class, name = "Science")
})
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;

    private String barcode;

    private int quantity;

    private double pricePerUnit;

    public Book() {
    }

    public Book(String name, String author, String barcode, int quantity, double pricePerUnit) {
        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (!VariableValidator.isValidBarcode(barcode)) {
            throw new IllegalArgumentException("Invalid barcode: " + barcode + " can only be positive numbers and alphabet characters up to 43characters in length");
        }
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (!VariableValidator.isValidQuantity(quantity)) {
            throw new IllegalArgumentException("Invalid quantity: " + quantity + " can only be positive integer.");
        }
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        if (!VariableValidator.isValidPricePerUnit(pricePerUnit)) {
            throw new IllegalArgumentException("Invalid pricePerUnit: " + pricePerUnit);
        }
        this.pricePerUnit = pricePerUnit;
    }

    // Updates the fields of an instance of the class with the values in the map
    // If the key matches one of the fields in the class call setter
    public void update(Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            if (field.equals("name")) {
                setName((String) value);
            } else if (field.equals("author")) {
                setAuthor((String) value);
            } else if (field.equals("barcode")) {
                setBarcode((String) value);
            } else if (field.equals("quantity")) {
                setQuantity((int) value);
            } else if (field.equals("pricePerUnit")) {
                setPricePerUnit((double) value);
            }
        }
    }
}