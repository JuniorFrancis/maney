package com.maney.api.service.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.model.Card;
import com.maney.api.repository.CardRepository;
import com.maney.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Long userId = userHandler.getCurrentUser().getId();
        return cardRepository.findByUserId(userId);
    }

    public Optional<Card> getCard(Long id) {

        checkNotNull(id);

        Long userId = userHandler.getCurrentUser().getId();
        Optional<Card> card = cardRepository.findByIdAndUserId(id, userId);

        isCardPresent(card, "Id {id} não encontrado");

        return card;
    }
}
