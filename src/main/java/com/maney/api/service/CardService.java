package com.maney.api.service;

import com.maney.api.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    Card create(Card card) ;

    List<Card> getCards();

    Optional<Card> getCard(Long id) ;


}
