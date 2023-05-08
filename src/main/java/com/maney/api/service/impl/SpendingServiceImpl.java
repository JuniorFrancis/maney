package com.maney.api.service.impl;

import com.maney.api.model.Card;
import com.maney.api.model.Spending;
import com.maney.api.repository.CardRepository;
import com.maney.api.repository.SpendingRepository;
import com.maney.api.service.SpendingService;
import com.maney.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    CardRepository cardRepository;

    private final Integer DAYS_TO_CLOSE_CARD = 7;

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

        cardIds.forEach( id -> {
            Optional<Card> currentCard = cardRepository.findById(Long.parseLong(id));

            List<LocalDate> period = DateUtils.parsePeriod(dateToQuery, currentCard.get(), this.DAYS_TO_CLOSE_CARD);

            LocalDate startDate = period.get(0);
            LocalDate endDate = period.get(1);

            spending.addAll(spendingRepository.findByDateSpendingBetweenAndCard(startDate, endDate, currentCard.get()));
        });


        return spending;
    }
}
