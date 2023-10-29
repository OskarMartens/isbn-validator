package com.example.isbnvalidator.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ISBN10CharactersValidationService {

    static ResponseEntity<String> validate10CharactersISBN(String ISBNString){

        if(ISBNString.matches("[0-9]+") && ISBNString.length() == 10 ) {
            return validate10DigitsISBNWithoutX(ISBNString);
        }

        String modifiedISBN = convertToStringBuilder(ISBNString);

        if (modifiedISBN.matches("[0-9]+") && modifiedISBN.length() == 11 ) {
            return validate10ISBNDigitsWithX(modifiedISBN, ISBNString);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", does not consist of digits");
    }

    private static String convertToStringBuilder(String ISBNString) {
        char lastChar = ISBNString.charAt(ISBNString.length() -1);
        boolean endsWithX = (lastChar == 'X' || lastChar == 'x');

        StringBuilder modifiedISBN = new StringBuilder(ISBNString);
        if(endsWithX) {
            modifiedISBN.deleteCharAt(ISBNString.length() -1);
            modifiedISBN.append("10");
        }
        return modifiedISBN.toString();
    }

    private static ResponseEntity<String> validate10DigitsISBNWithoutX(String ISBNString) {
        int multiplier = 10;
        int sum = 0;

        for (int i = 0; i < ISBNString.length(); i++) {
            int digit = Character.getNumericValue(ISBNString.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNString + ", is a valid ISBN");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", is not a valid ISBN");
    }

    private static ResponseEntity<String> validate10ISBNDigitsWithX(String ISBNString, String ISBNWithX) {
        int multiplier = 10;
        int sum = 10;

        for (int i = 0; i < ISBNString.length() -2; i++) {
            int digit = Character.getNumericValue(ISBNString.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNWithX + ", is a valid ISBN");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNWithX + ", is not a valid ISBN");
    }
}
