package com.example.isbnvalidator.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISBN10CharacterValidationServiceTest {

    ISBNValidationService isbnValidationService = new ISBNValidationService();

    @Test
    public void confirm_10_characters_is_valid_ISBN_without_X() throws IOException, InterruptedException {

        String draculaISBN10 = "1503261387";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(draculaISBN10);
        assertEquals(HttpStatus.OK, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + draculaISBN10 + ", is a valid ISBN. And the book is: Dracula", methodResponse1.getBody());

        String theHitchHikersGuideToTheGalaxyISBN10 = "0330508113";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(theHitchHikersGuideToTheGalaxyISBN10);
        assertEquals(HttpStatus.OK, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + theHitchHikersGuideToTheGalaxyISBN10 + ", is a valid ISBN. And the book is: The Hitch Hiker's Guide to the Galaxy", methodResponse2.getBody());
    }

    @Test
    public void confirm_10_characters_is_NOT_valid_ISBN_without_X() throws IOException, InterruptedException {

        String nonFunctioningISBN1 = "1503261382";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(nonFunctioningISBN1);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + nonFunctioningISBN1 + ", is not a valid ISBN.", methodResponse1.getBody());

        String nonFunctioningISBN2 = "0330508112";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(nonFunctioningISBN2);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + nonFunctioningISBN2 + ", is not a valid ISBN.", methodResponse2.getBody());
    }

    @Test
    public void confirm_10_digits_is_valid_ISBN_with_uppercase_X() throws IOException, InterruptedException {

        String ISBNNumber = "043942089X";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.OK, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", is a valid ISBN. And the book is: Herr der Diebe", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_lowercase_x() throws IOException, InterruptedException {

        String ISBNNumber = "043942089x";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", does not consist of digits.", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_uppercase_X() throws IOException, InterruptedException {

        String ISBNNumber = "043942087X";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", is not a valid ISBN.", methodResponse.getBody());
    }

    @Test
    public void confirm_10_non_digits_string_is_NOT_valid() throws IOException, InterruptedException {

        String ISBNExample = "abcdefghij";
        ResponseEntity<String> methodResponse =  isbnValidationService.validateISBN(ISBNExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNExample + ", does not consist of digits.", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_Y_ending() throws IOException, InterruptedException {

        String ISBNNumber = "043942089Y";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", does not consist of digits.", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_random_letters() throws IOException, InterruptedException {

        String ISBNNumber = "hej9T2089Y";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", does not consist of digits.", methodResponse.getBody());
    }
}
