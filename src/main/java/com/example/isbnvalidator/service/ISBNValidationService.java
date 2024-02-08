package com.example.isbnvalidator.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ISBNValidationService {

    public ResponseEntity<String> validateISBN(String ISBNString) throws IOException, InterruptedException {

        var isbn10Validation = new ISBN10CharactersValidationService();
        var isbn13Validation = new ISBN13CharacterValidationService();

        if(ISBNString.length() == 10)
            return isbn10Validation.validate10CharactersISBN(ISBNString);

        else if(ISBNString.length() == 13)
            return isbn13Validation.validate13CharactersISBN(ISBNString);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The string: " + ISBNString + " is not valid as it is not 10 nor 13 characters long.");
    }
}
