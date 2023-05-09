package com.maney.api.service.impl;

import com.maney.api.model.Card;
import com.maney.api.repository.CardRepository;
import com.maney.api.service.CardService;
import com.maney.api.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.maney.api.utils.Validator.checkNotNull;
import static com.maney.api.utils.Validator.isCardPresent;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;
    public Card create(Card card) {

        return cardRepository.save(card);
    }

    public List<Card> getCards() {

        return cardRepository.findAll();
    }

    public Optional<Card> getCard(Long id) {

        checkNotNull(id);

        Optional<Card> card = cardRepository.findById(id);

        isCardPresent(card, "Id {id} não encontrado");

        return card;
    }
}
