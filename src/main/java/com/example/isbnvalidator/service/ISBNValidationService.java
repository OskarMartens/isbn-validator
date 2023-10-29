package com.example.isbnvalidator.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ISBNValidationService {


    public ResponseEntity<String> validateISBN(String ISBNNumber) {

        if(ISBNNumber.length() == 10) {
            return validate10DigitsISBN(ISBNNumber);
        }

        else if(ISBNNumber.length() == 13) {
            return validate13DigitsISBN(ISBNNumber);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The string: " + ISBNNumber + " is not valid as it is not 10 nor 13 characters long");
    }

    private ResponseEntity<String> validate10DigitsISBN(String ISBNNumber){

        StringBuilder modifiedISBN = convertToStringBuilder(ISBNNumber);

        if(modifiedISBN.toString().matches("[0-9]+") && modifiedISBN.length() == 10 ) {
            return validate10DigitsISBNWithoutX(modifiedISBN.toString());
        }

        else if (modifiedISBN.toString().matches("[0-9]+") && modifiedISBN.length() == 11 ) {
            return validate10ISBNDigitsWithX(modifiedISBN.toString(), ISBNNumber);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNNumber + ", does not comprise of digits");
    }

    private static StringBuilder convertToStringBuilder(String ISBNNumber) {
        char lastChar = ISBNNumber.charAt(ISBNNumber.length() -1);
        boolean endsWithX = (lastChar == 'X' || lastChar == 'x');

        StringBuilder modifiedISBN = new StringBuilder(ISBNNumber);
        if(endsWithX) {
            modifiedISBN.deleteCharAt(ISBNNumber.length() -1);
            modifiedISBN.append("10");
        }
        return modifiedISBN;
    }

    private ResponseEntity<String> validate10DigitsISBNWithoutX(String ISBN) {
        int multiplier = 10;
        int sum = 0;

        for (int i = 0; i < ISBN.length(); i++) {
            int digit = Character.getNumericValue(ISBN.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBN + ", is a valid ISBN");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBN + ", is not a valid ISBN");
    }


    private ResponseEntity<String> validate10ISBNDigitsWithX(String ISBN, String ISBNWithX) {
        int multiplier = 10;
        int sum = 10;

        for (int i = 0; i < ISBN.length() -2; i++) {
            int digit = Character.getNumericValue(ISBN.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNWithX + ", is a valid ISBN");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNWithX + ", is not a valid ISBN");
    }


    private ResponseEntity<String> validate13DigitsISBN(String ISBNNumber) {
        if(!ISBNNumber.matches("[0-9]+")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNNumber + ", does not comprise of digits");
        }

        int sum = 0;
        for (int i = 0; i < ISBNNumber.length(); i++) {
            int digit = Character.getNumericValue(ISBNNumber.charAt(i));

            if(i % 2 == 0){
                sum += digit;
            }
            else {
                sum += digit * 3;
            }
        }

        if(sum % 10 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNNumber + " is a valid ISBN");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided number: " + ISBNNumber + " is not a valid ISBN");
    }
}
