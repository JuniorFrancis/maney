package com.maney.api.service.impl;

import com.maney.api.model.Card;
import com.maney.api.model.Revenue;
import com.maney.api.model.Spending;
import com.maney.api.model.responses.AccountingResponse;
import com.maney.api.repository.SpendingRepository;
import com.maney.api.service.AccountingService;
import com.maney.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.maney.api.utils.AccountingHandler.toAccountingResponse;
import static com.maney.api.utils.Validator.checkNotNull;

@Service
public class AccountingServiceImpl implements AccountingService {

    @Autowired
    DateUtils dateUtils;

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    RevenueServiceImpl revenueService;
    public AccountingResponse overview(LocalDate dateToQuery) {

        checkNotNull(dateToQuery);

        ArrayList<Spending> spending = new ArrayList<>();
        //TODO implementar metodo no service de revenues para buscar de recebidos por period
        List<Revenue> revenues = revenueService.getRevenues();
        Map<Card, List<LocalDate>> periods = dateUtils.parsePeriodToAllCards(dateToQuery);

        periods.forEach( (card, dates) -> {
            List<Spending> spendings = spendingRepository.findByDateSpendingBetweenAndCard(dates.get(0), dates.get(1), card);
            spending.addAll(spendings);
        });


        return toAccountingResponse(spending, revenues, dateToQuery);
    }
}
