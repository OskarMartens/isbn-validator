package com.example.isbnvalidator.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidationServiceTest {

    ISBNValidationService isbnValidationService = new ISBNValidationService();

    @Test
    public void confirm_10_characters_is_valid_ISBN_without_x() {

        String ISBNExample1 = "0143039431";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(ISBNExample1);
        assertEquals(HttpStatus.OK, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + ISBNExample1 + ", is a valid ISBN", methodResponse1.getBody());


        String ISBNExample2 = "9185057819";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(ISBNExample2);
        assertEquals(HttpStatus.OK, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + ISBNExample2 + ", is a valid ISBN", methodResponse2.getBody());
    }

    @Test
    public void confirm_10_non_digits_string_is_NOT_valid(){

        String ISBNExample = "abcdefghij";
        ResponseEntity<String> methodResponse =  isbnValidationService.validateISBN(ISBNExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNExample + ", does not comprise of digits", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_valid_ISBN_with_x(){

        String ISBNNumber = "043942089X";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.OK, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", is a valid ISBN", methodResponse.getBody());
    }

    @Test
    public void confirm_is_neither_10_or_13_characters_long(){

        String ISBNExample = "01431";
        ResponseEntity<String> methodResponse = isbnValidationService.validateISBN(ISBNExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The string: " + ISBNExample + " is not valid as it is not 10 nor 13 characters long", methodResponse.getBody());
    }

    @Test
    public void confirm_13_non_digits_string_is_NOT_valid(){
        String ISBNExample = "abcdefghijklm";
        ResponseEntity<String> methodResponse = isbnValidationService.validateISBN(ISBNExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNExample + ", does not comprise of digits", methodResponse.getBody());
    }

    @Test
    public void confirm_13_digits_is_valid(){
        String mobyDickISBN = "9781503280786";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(mobyDickISBN);
        assertEquals(HttpStatus.OK, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + mobyDickISBN + " is a valid ISBN", methodResponse1.getBody());

        String cityOfMyDreamsISBN = "9781572160880";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(cityOfMyDreamsISBN);
        assertEquals(HttpStatus.OK, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + cityOfMyDreamsISBN + " is a valid ISBN", methodResponse2.getBody());
    }
}