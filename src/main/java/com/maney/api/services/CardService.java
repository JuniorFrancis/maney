package com.maney.api.services;

import com.maney.api.models.Card;

import java.util.List;

public interface CardService {

    void create(Card card) ;

    List<Card> getCards();

    Card getCard(Long id) ;


}
