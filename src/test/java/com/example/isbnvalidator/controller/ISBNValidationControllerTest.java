package com.example.isbnvalidator.controller;

import com.example.isbnvalidator.service.ISBNValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ISBNValidationController.class)
@ExtendWith(MockitoExtension.class)
class ISBNValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISBNValidationService isbnValidationService;

    @Test
    public void confirm_10_digits_without_x_is_valid() throws Exception {

        String ISBNString = "0143039431";
        ResultActions response = mockMvc.perform(post("/api/ISBNValidation/" + ISBNString));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void confirm_incorrect_url_returns_404() throws Exception {

        String ISBNString = "0143039431";
        ResultActions response = mockMvc.perform(post("/api/wrong-url/" + ISBNString));
        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}