package com.example.isbnvalidator.service;

import com.example.isbnvalidator.api.ApiCall;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ISBN13CharacterValidationService {

    public final ResponseEntity<String> validate13CharactersISBN(String ISBNString) throws IOException, InterruptedException {
        if(!ISBNString.matches("[0-9]+")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + ", does not consist of digits.");
        }

        int sum = 0;
        for (int i = 0; i < ISBNString.length(); i++) {
            int digit = Character.getNumericValue(ISBNString.charAt(i));

            if(i % 2 == 0){
                sum += digit;
            }
            else {
                sum += digit * 3;
            }
        }

        if(sum % 10 == 0) {
            ApiCall apiCall = new ApiCall();
            String bookTitle = apiCall.getBookTitle(ISBNString);
            return ResponseEntity.status(HttpStatus.OK).body("The provided string: " + ISBNString + " is a valid ISBN. And the book is: " + bookTitle);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided string: " + ISBNString + " is not a valid ISBN.");
    }
}
