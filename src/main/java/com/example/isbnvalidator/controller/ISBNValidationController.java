package com.example.isbnvalidator.controller;

import com.example.isbnvalidator.service.ISBNValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ISBN/")
public class ISBNValidationController {

    private final ISBNValidationService isbnValidationService;

    @Autowired
    public ISBNValidationController(ISBNValidationService isbnValidationService) {
        this.isbnValidationService = isbnValidationService;
    }

    @PostMapping("{ISBNString}")
    public ResponseEntity<String> validateISBN(@PathVariable ("ISBNString") String ISBNString){
        return isbnValidationService.validateISBN(ISBNString);
    }
}
