package com.demodemoDematic.entryLevelBookStore.validators;

import java.util.regex.Pattern;

public class VariableValidator {

    // Wanted to use regex for validations not  sure if its scalable choice, looks nice though.
    private static final Pattern SCIENCE_INDEX_PATTERN = Pattern.compile("^[1-9]|10$");
    private static final Pattern RELEASE_YEAR_PATTERN =
            Pattern.compile("^(-?3480|-?[0-4]\\d{3}|-?[0-3]\\d{2}|-?[0-2]\\d{1}|-?19[0-9]{2}|1900|[0-9]{4}|[0-9]{1,3})$");
    private static final Pattern BARCODE_PATTERN = Pattern.compile("^[A-Za-z0-9]{1,43}$");
    private static final Pattern PRICE_PER_UNIT_PATTERN = Pattern.compile("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^[0-9]*$");

    public static boolean isValidScienceIndex(int scienceIndex) {
        return SCIENCE_INDEX_PATTERN.matcher(Integer.toString(scienceIndex)).matches();
    }

    public static boolean isValidReleaseYear(int releaseYear) {
        return RELEASE_YEAR_PATTERN.matcher(Integer.toString(releaseYear)).matches();
    }

    public static boolean isValidBarcode(String barcode) {
        return BARCODE_PATTERN.matcher(barcode).matches();
    }

    public static boolean isValidPricePerUnit(double pricePerUnit) {
        return PRICE_PER_UNIT_PATTERN.matcher(Double.toString(pricePerUnit)).matches();
    }

    public static boolean isValidQuantity(int quantity) {
        return QUANTITY_PATTERN.matcher(Integer.toString(quantity)).matches();
    }

}
