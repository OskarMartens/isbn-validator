package com.example.isbnvalidator.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidationServiceTest {

    ISBNValidationService isbnValidationService = new ISBNValidationService();

    @Test
    public void confirm_is_neither_10_or_13_characters_long() throws IOException, InterruptedException {

        String ISBNShortExample = "01431";
        ResponseEntity<String> methodResponse = isbnValidationService.validateISBN(ISBNShortExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The string: " + ISBNShortExample + " is not valid as it is not 10 nor 13 characters long.", methodResponse.getBody());

        String ISBNLongExample = "123456789987654321";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(ISBNLongExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse2.getStatusCode());
        assertEquals("The string: " + ISBNLongExample + " is not valid as it is not 10 nor 13 characters long.", methodResponse2.getBody());

    }
}