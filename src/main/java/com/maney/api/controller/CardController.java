package com.maney.api.controller;

import com.maney.api.model.Card;
import com.maney.api.service.CardService;
import com.maney.api.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @Autowired
    CardServiceImpl cardService;

    @ResponseBody
    @PostMapping
    public Card create(@RequestBody Card card) {

        return cardService.create(card);
    }

    @ResponseBody
    @GetMapping
    public List<Card> getCards() {

        return cardService.getCards();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Optional<Card> getCard(@PathVariable("id") Long id) {

        return cardService.getCard(id);
    }
}
