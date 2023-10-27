package com.example.isbnvalidator.controller;

import com.example.isbnvalidator.service.ISBNValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ISBNController {

    private final ISBNValidationService isbnValidationService;

    @Autowired
    public ISBNController(ISBNValidationService isbnValidationService) {
        this.isbnValidationService = isbnValidationService;
    }
}
