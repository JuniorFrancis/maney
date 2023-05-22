package com.maney.api.service.impl;

import com.maney.api.model.Card;
import com.maney.api.model.Revenue;
import com.maney.api.model.Spending;
import com.maney.api.model.responses.AccountingResponse;
import com.maney.api.repository.SpendingRepository;
import com.maney.api.service.AccountingService;
import com.maney.api.handlers.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.maney.api.handlers.AccountingHandler.toAccountingResponse;
import static com.maney.api.handlers.ValidatorHandler.checkNotNull;

@Service
public class AccountingServiceImpl implements AccountingService {

    @Autowired
    DateHandler dateHandler;

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    RevenueServiceImpl revenueService;
    public AccountingResponse overview(LocalDate dateToQuery) {

        checkNotNull(dateToQuery);

        ArrayList<Spending> spending = new ArrayList<>();
        List<Revenue> revenues = revenueService.getRevenues();
        Map<Card, List<LocalDate>> periods = dateHandler.parsePeriodToAllCards(dateToQuery);

        periods.forEach( (card, dates) -> {
            List<Spending> currentSpending = spendingRepository.findByDateSpendingBetweenAndCard(dates.get(0), dates.get(1), card);
            spending.addAll(currentSpending);
        });


        return toAccountingResponse(spending, revenues, dateToQuery);
    }
}
