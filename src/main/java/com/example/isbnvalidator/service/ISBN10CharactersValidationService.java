package com.example.isbnvalidator.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ISBN10CharactersValidationService {

    static ResponseEntity<String> validate10CharactersISBN(String ISBNString){

        if(ISBNString.matches("[0-9]+") && ISBNString.length() == 10 ) {
            return validateISBN(ISBNString, null);
        }

        String modifiedISBN = convertUsingStringBuilder(ISBNString);

        if (modifiedISBN.matches("[0-9]+") && modifiedISBN.length() == 9 ) {
            return validateISBN(modifiedISBN, ISBNString);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", does not consist of digits.");
    }

    private static String convertUsingStringBuilder(String ISBNString) {
        char lastChar = ISBNString.charAt(ISBNString.length() -1);
        boolean endsWithX = (lastChar == 'X');

        StringBuilder modifiedISBN = new StringBuilder(ISBNString);
        if(endsWithX) {
            modifiedISBN.deleteCharAt(ISBNString.length() -1);
        }
        return modifiedISBN.toString();
    }

    private static ResponseEntity<String> validateISBN(String ISBNString, String ISBNWithX){
        int multiplier = 10;
        int sum = 0;

        if (ISBNString.length() == 9){
            sum += 10;
        }

        for (int i = 0; i < ISBNString.length(); i++) {
            int digit = Character.getNumericValue(ISBNString.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0 && ISBNWithX == null) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNString + ", is a valid ISBN.");
        }
        else if (sum % 11 == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNWithX + ", is a valid ISBN.");
        }
        else if (ISBNWithX == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", is not a valid ISBN.");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNWithX + ", is not a valid ISBN.");
    }
}
