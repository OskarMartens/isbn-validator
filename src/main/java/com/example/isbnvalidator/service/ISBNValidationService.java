package com.example.isbnvalidator.service;

import org.springframework.stereotype.Service;

@Service
public class ISBNValidationService {

    public String validateISBN(String ISBNNumber) {

        if(ISBNNumber.length() == 10) {
            return validate10DigitsISBN(ISBNNumber);
        }

        else if(ISBNNumber.length() == 13) {
            return validate13DigitsISBN(ISBNNumber);
        }

        return "The string: " + ISBNNumber + " is not valid as it is not 10 nor 13 characters long";
    }


    private String validate10DigitsISBN(String ISBNNumber){

        char lastChar = ISBNNumber.charAt(ISBNNumber.length() -1);
        boolean endsWithX = (lastChar == 'X' || lastChar == 'x');

        StringBuilder modifiedISBN = new StringBuilder(ISBNNumber);
        if(endsWithX) {
            modifiedISBN.deleteCharAt(ISBNNumber.length() -1);
            modifiedISBN.append("10");
        }

        if(modifiedISBN.toString().matches("[0-9]+") && modifiedISBN.length() == 10 ) {
            return validate10DigitsISBNWithoutX(modifiedISBN.toString());
        }

        else if (modifiedISBN.toString().matches("[0-9]+") && modifiedISBN.length() == 11 ) {

            return validate10ISBNDigitsWithX(modifiedISBN.toString(), ISBNNumber);
        }

        return "The provided string: " + ISBNNumber + ", does not comprise of digits";
    }

    private String validate10DigitsISBNWithoutX(String ISBN) {
        int multiplier = 10;
        int sum = 0;

        for (int i = 0; i < ISBN.length(); i++) {
            int digit = Character.getNumericValue(ISBN.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            return "The provided string: " + ISBN + ", is a valid ISBN";
        }

        return "The provided string: " + ISBN + ", is not a valid ISBN";
    }


    private String validate10ISBNDigitsWithX(String ISBN, String ISBNWithX) {
        int multiplier = 10;
        int sum = 10;

        for (int i = 0; i < ISBN.length() -2; i++) {
            int digit = Character.getNumericValue(ISBN.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            return "The provided string: " + ISBNWithX + ", is a valid ISBN";
        }

        return "The provided string: " + ISBNWithX + ", is not a valid ISBN";
    }







    private String validate13DigitsISBN(String ISBNNumber) {
        return "The provided number: " + ISBNNumber + " has 13 digits";
    }
}
