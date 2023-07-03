package com.maney.api.services.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.models.Card;
import com.maney.api.models.Spending;
import com.maney.api.repositories.CardRepository;
import com.maney.api.repositories.SpendingRepository;
import com.maney.api.services.SpendingService;
import com.maney.api.handlers.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maney.api.handlers.ValidatorHandler.checkNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    public SpendingServiceImpl(DateHandler dateHandler, SpendingRepository spendingRepository, CardRepository cardRepository, UserHandler userHandler) {
        this.dateHandler = dateHandler;
        this.spendingRepository = spendingRepository;
        this.cardRepository = cardRepository;
        this.userHandler = userHandler;
    }

    private final DateHandler dateHandler;
    private final SpendingRepository spendingRepository;
    private final CardRepository cardRepository;
    private final UserHandler userHandler;

    @Override
    public void register(List<Spending> spending) {
        spending.forEach( spent -> {
            spent.setUser(userHandler.getCurrentUser());
            spendingRepository.save(spent);
        });
    }

    @Override
    public List<Spending> getSpending() {
        Long userId = userHandler.getCurrentUserId();
        return spendingRepository.findByUserId(userId);
    }

    @Override
    public Spending getSpent(Long id) {
        Long userId = userHandler.getCurrentUserId();
        Spending spent = spendingRepository.findByIdAndUserId(id, userId).orElseThrow(
            () -> new IllegalArgumentException("Spending not found")
        );

        return spent;
    }

    @Override
    public List<Spending> byPeriod(LocalDate dateToQuery, List<String> cardIds){
        //TODO FAZER PAGINAÇÃO DESSE RETORNO
        ArrayList<Spending> spending = new ArrayList<>();
        Long userId = userHandler.getCurrentUserId();

        checkNotNull(dateToQuery);

        cardIds.forEach( id -> {
            Card currentCard = cardRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new IllegalArgumentException("Card not found")
            );

            List<LocalDate> period = dateHandler.parsePeriod(dateToQuery, currentCard);

            LocalDate startDate = period.get(0);
            LocalDate endDate = period.get(1);

            spending.addAll(
                    spendingRepository.findByDateSpendingBetweenAndCardAndUserId(
                            startDate,
                            endDate,
                            currentCard,
                            userId
                    ));
        });

        return spending;
    }
}
