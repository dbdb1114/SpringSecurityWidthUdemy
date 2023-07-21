package com.eazybytes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {
    @GetMapping("/myCard")
    public String getBalanceDetails(){
        return "Here are the card details from the DB";
    }
}
