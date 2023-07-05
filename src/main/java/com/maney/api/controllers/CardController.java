package com.maney.api.controllers;

import com.maney.api.models.Card;
import com.maney.api.services.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @Autowired
    CardServiceImpl cardService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Card card) {

        cardService.create(card);
    }

    @GetMapping
    @ResponseBody
    public List<Card> getCards() {

        return cardService.getCards();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Card getCard(@PathVariable("id") Long id) {

        return cardService.getCard(id);
    }
}
