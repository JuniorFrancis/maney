package com.maney.api.service.impl;

import com.maney.api.model.Card;
import com.maney.api.model.Spending;
import com.maney.api.repository.CardRepository;
import com.maney.api.repository.SpendingRepository;
import com.maney.api.service.SpendingService;
import com.maney.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maney.api.utils.Validator.checkNotNull;
import static com.maney.api.utils.Validator.isCardPresent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    DateUtils dateUtils;
    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public void register(Spending spending) {

        Optional<Card> card = cardRepository.findById(spending.getCard().getId());

        spendingRepository.save(spending);

    }

    @Override
    public List<Spending> getSpending() {

        return spendingRepository.findAll();
    }

    @Override
    public Optional<Spending> getSpent(Long id) {

        return spendingRepository.findById(id);
    }

    @Override
    public List<Spending> byPeriod(LocalDate dateToQuery, List<String> cardIds){
        //TODO FAZER PAGINAÇÃO DESSE RETORNO
        ArrayList<Spending> spending = new ArrayList<>();

        checkNotNull(dateToQuery);

        cardIds.forEach( id -> {
            Optional<Card> currentCard = cardRepository.findById(Long.parseLong(id));

            isCardPresent(currentCard);
            List<LocalDate> period = dateUtils.parsePeriod(dateToQuery, currentCard.get());

            LocalDate startDate = period.get(0);
            LocalDate endDate = period.get(1);

            spending.addAll(spendingRepository.findByDateSpendingBetweenAndCard(startDate, endDate, currentCard.get()));
        });


        return spending;
    }
}
