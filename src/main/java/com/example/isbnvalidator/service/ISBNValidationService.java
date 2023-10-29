package com.example.isbnvalidator.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ISBNValidationService {

    public ResponseEntity<String> validateISBN(String ISBNString) {

        if(ISBNString.length() == 10) {
            return ISBN10CharactersValidationService.validate10CharactersISBN(ISBNString);
        }

        else if(ISBNString.length() == 13) {
            return ISBN13CharacterValidationService.validate13CharactersISBN(ISBNString);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The string: " + ISBNString + " is not valid as it is not 10 nor 13 characters long.");
    }
}
