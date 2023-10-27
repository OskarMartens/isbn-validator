package com.example.isbnvalidator.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidationServiceTest {

    ISBNValidationService isbnValidationService = new ISBNValidationService();

    @Test
    public void confirm_10_characters_is_valid_ISBN_without_x() {

        String ISBNExample1 = "0143039431";
        String methodResponse = isbnValidationService.validateISBN(ISBNExample1);

        String ISBNExample2 = "9185057819";
        String methodResponse2 = isbnValidationService.validateISBN(ISBNExample2);


        assertEquals("The provided string: " + ISBNExample1 + ", is a valid ISBN", methodResponse);
        assertEquals("The provided string: " + ISBNExample2 + ", is a valid ISBN", methodResponse2);
    }

    @Test
    public void confirm_10_non_digits_string_is_not_valid(){

        String ISBNExample = "abcdefghij";
        String methodResponse =  isbnValidationService.validateISBN(ISBNExample);

        assertEquals("The provided string: " + ISBNExample + ", does not comprise of digits", methodResponse);
    }

    @Test
    public void confirm_is_valid_ISBN_with_x(){

        String ISBNNumber = "043942089X";
        String methodResponse = isbnValidationService.validateISBN(ISBNNumber);

        assertEquals("The provided string: " + ISBNNumber + ", is a valid ISBN", methodResponse);
    }

    @Test
    public void confirm_is_neither_10_or_13_characters_long(){

        String ISBNNumber = "01431";
        String methodResponse = isbnValidationService.validateISBN(ISBNNumber);

        assertEquals("The string: " + ISBNNumber + " is not valid as it is not 10 nor 13 characters long", methodResponse);
    }
}