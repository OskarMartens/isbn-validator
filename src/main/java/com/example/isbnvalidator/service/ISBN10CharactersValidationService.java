package com.example.isbnvalidator.service;

import com.example.isbnvalidator.api.ApiCall;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ISBN10CharactersValidationService {


    public ResponseEntity<String> validate10CharactersISBN(String ISBNString) throws IOException, InterruptedException {

        if(ISBNString.matches("[0-9]+") && ISBNString.length() == 10 ) {
            return validate10DigitsISBNWithoutX(ISBNString);
        }

        String modifiedISBN = convertUsingStringBuilder(ISBNString);

        if (modifiedISBN.matches("[0-9]+") && modifiedISBN.length() == 11 ) {
            return validate10ISBNDigitsWithX(modifiedISBN, ISBNString);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", does not consist of digits.");
    }

    private ResponseEntity<String> validate10DigitsISBNWithoutX(String ISBNString) throws IOException, InterruptedException {
        int multiplier = 10;
        int sum = 0;

        for (int i = 0; i < ISBNString.length(); i++) {
            int digit = Character.getNumericValue(ISBNString.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            ApiCall apiCall = new ApiCall();
            String bookTitle = apiCall.getBookTitle(ISBNString);
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNString + ", is a valid ISBN. And the book is: " + bookTitle);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", is not a valid ISBN.");
    }

    private String convertUsingStringBuilder(String ISBNString) {
        char lastChar = ISBNString.charAt(ISBNString.length() -1);
        boolean endsWithX = (lastChar == 'X');

        StringBuilder modifiedISBN = new StringBuilder(ISBNString);
        if(endsWithX) {
            modifiedISBN.deleteCharAt(ISBNString.length() -1);
            modifiedISBN.append("10");
        }
        return modifiedISBN.toString();

    }

    private ResponseEntity<String> validate10ISBNDigitsWithX(String modifiedISBN, String ISBNWithX) throws IOException, InterruptedException {
        int multiplier = 10;
        int sum = 10;

        for (int i = 0; i < modifiedISBN.length() -2; i++) {
            int digit = Character.getNumericValue(modifiedISBN.charAt(i));
            sum += digit * multiplier;
            multiplier -= 1;
        }

        if(sum % 11 == 0) {
            ApiCall apiCall = new ApiCall();
            String bookTitle = apiCall.getBookTitle(ISBNWithX);
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNWithX + ", is a valid ISBN. And the book is: " + bookTitle);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNWithX + ", is not a valid ISBN.");
    }
}
