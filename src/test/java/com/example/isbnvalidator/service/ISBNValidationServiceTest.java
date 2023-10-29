package com.example.isbnvalidator.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidationServiceTest {

    ISBNValidationService isbnValidationService = new ISBNValidationService();

    @Test
    public void confirm_10_characters_is_valid_ISBN_without_x() {

        String draculaISBN10 = "1503261387";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(draculaISBN10);
        assertEquals(HttpStatus.OK, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + draculaISBN10 + ", is a valid ISBN", methodResponse1.getBody());

        String theHitchHikersGuideToTheGalaxyISBN10 = "0330508113";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(theHitchHikersGuideToTheGalaxyISBN10);
        assertEquals(HttpStatus.OK, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + theHitchHikersGuideToTheGalaxyISBN10 + ", is a valid ISBN", methodResponse2.getBody());
    }

    @Test
    public void confirm_10_characters_is_NOT_valid_ISBN_without_x() {

        String nonFunctioningISBN1 = "1503261382";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(nonFunctioningISBN1);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + nonFunctioningISBN1 + ", is not a valid ISBN", methodResponse1.getBody());

        String nonFunctioningISBN2 = "0330508112";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(nonFunctioningISBN2);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + nonFunctioningISBN2 + ", is not a valid ISBN", methodResponse2.getBody());
    }

    @Test
    public void confirm_10_digits_is_valid_ISBN_with_x(){

        String ISBNNumber = "043942089X";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.OK, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", is a valid ISBN", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_x(){

        String ISBNNumber = "043942087X";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", is not a valid ISBN", methodResponse.getBody());
    }

    @Test
    public void confirm_10_non_digits_string_is_NOT_valid(){

        String ISBNExample = "abcdefghij";
        ResponseEntity<String> methodResponse =  isbnValidationService.validateISBN(ISBNExample);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNExample + ", does not consist of digits", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_y_ending(){

        String ISBNNumber = "043942089Y";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", does not consist of digits", methodResponse.getBody());
    }

    @Test
    public void confirm_10_digits_is_NOT_valid_ISBN_with_random_letters(){

        String ISBNNumber = "hej9T2089Y";
        ResponseEntity <String> methodResponse = isbnValidationService.validateISBN(ISBNNumber);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse.getStatusCode());
        assertEquals("The provided string: " + ISBNNumber + ", does not consist of digits", methodResponse.getBody());
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
        assertEquals("The provided string: " + ISBNExample + ", does not consist of digits", methodResponse.getBody());
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

    @Test
    public void confirm_13_digits_is_NOT_valid(){
        String mobyDickISBN = "9781503280783";
        ResponseEntity<String> methodResponse1 = isbnValidationService.validateISBN(mobyDickISBN);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse1.getStatusCode());
        assertEquals("The provided string: " + mobyDickISBN + " is not a valid ISBN", methodResponse1.getBody());

        String cityOfMyDreamsISBN = "9781572160883";
        ResponseEntity<String> methodResponse2 = isbnValidationService.validateISBN(cityOfMyDreamsISBN);
        assertEquals(HttpStatus.BAD_REQUEST, methodResponse2.getStatusCode());
        assertEquals("The provided string: " + cityOfMyDreamsISBN + " is not a valid ISBN", methodResponse2.getBody());
    }
}