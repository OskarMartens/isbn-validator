package com.example.isbnvalidator.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidationServiceTest {

    ISBNValidationService isbnValidationService = new ISBNValidationService();

    @Test
    public void confirm_is_neither_10_or_13_characters_long(){

        String ISBNExample = "01431";
        ResponseEntity<String> methodResponse = isbnValidationService.validateISBN(ISBNExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The string: " + ISBNExample + " is not valid as it is not 10 nor 13 characters long.", methodResponse.getBody());
    }
}