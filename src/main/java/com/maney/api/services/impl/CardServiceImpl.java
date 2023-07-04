package com.maney.api.services.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.models.Card;
import com.maney.api.repositories.CardRepository;
import com.maney.api.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.maney.api.handlers.ValidatorHandler.checkNotNull;
import static com.maney.api.handlers.ValidatorHandler.isCardPresent;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserHandler userHandler) {
        this.cardRepository = cardRepository;
        this.userHandler = userHandler;
    }

    private final CardRepository cardRepository;

    private final UserHandler userHandler;

    public Card create(Card card) {
        card.setUser(userHandler.getCurrentUser());
        return cardRepository.save(card);
    }

    public List<Card> getCards() {
        Long userId = userHandler.getCurrentUserId();
        return cardRepository.findByUserId(userId);
    }

    public Card getCard(Long id) {

        Long userId = userHandler.getCurrentUser().getId();
        Card card = cardRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new IllegalArgumentException("received card id not exists")
        );

        return card;
    }
}
