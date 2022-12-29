package com.demodemoDematic.entryLevelBookStore.models;

import com.demodemoDematic.entryLevelBookStore.validators.VariableValidator;
import jakarta.persistence.Entity;

import java.util.Map;

// Bean
@Entity
public class ScienceJournal extends Book {

    private int scienceIndex;

    public ScienceJournal() {
    }

    public ScienceJournal(String name, String author, String barcode, int quantity, double pricePerUnit, int scienceIndex) {
        super(name, author, barcode, quantity, pricePerUnit);
        this.scienceIndex = scienceIndex;
    }

    public int getScienceIndex() {
        return scienceIndex;
    }

    public void setScienceIndex(int scienceIndex) {
        if (!VariableValidator.isValidScienceIndex(scienceIndex)) {
            throw new IllegalArgumentException("Invalid scienceIndex: " + scienceIndex + " must be between 1 and 10 (1 and 10 including)");
        }
        this.scienceIndex = scienceIndex;
    }

    // Adding additional parameters to update
    @Override
    public void update(Map<String, Object> parameters) {
        super.update(parameters);
            if (parameters.containsKey("scienceIndex")) {
                setScienceIndex((int) parameters.get("scienceIndex"));
        }
    }


}

