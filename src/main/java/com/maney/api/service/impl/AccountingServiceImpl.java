package com.maney.api.service.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.model.Card;
import com.maney.api.model.Revenue;
import com.maney.api.model.Spending;
import com.maney.api.model.responses.AccountingResponse;
import com.maney.api.repository.SpendingRepository;
import com.maney.api.repository.projects.ProjectTagAndAmount;
import com.maney.api.service.AccountingService;
import com.maney.api.handlers.DateHandler;
import jakarta.annotation.Nullable;
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
    public AccountingServiceImpl(DateHandler dateHandler, SpendingRepository spendingRepository, RevenueServiceImpl revenueService, UserHandler userHandler) {
        this.dateHandler = dateHandler;
        this.spendingRepository = spendingRepository;
        this.revenueService = revenueService;
        this.userHandler = userHandler;
    }

    private final DateHandler dateHandler;

    private final SpendingRepository spendingRepository;

    private final RevenueServiceImpl revenueService;

    private final UserHandler userHandler;

    public AccountingResponse overview(LocalDate dateToQuery) {
        Long userId = userHandler.getCurrentUser().getId();
        checkNotNull(dateToQuery);

        ArrayList<Spending> spending = new ArrayList<>();
        List<Revenue> revenues = revenueService.getRevenues();
        Map<Card, List<LocalDate>> periods = dateHandler.parsePeriodToAllCards(dateToQuery);

        periods.forEach( (card, dates) -> {
            List<Spending> currentSpending = spendingRepository.findByDateSpendingBetweenAndCardAndUserId(dates.get(0), dates.get(1), card, userId);
            spending.addAll(currentSpending);
        });

        return toAccountingResponse(spending, revenues, dateToQuery);
    }

    public List<ProjectTagAndAmount> expansiveTags(@Nullable String initialPeriod, @Nullable String finalPeriod){

        LocalDate finalPeriodToQuery = LocalDate.now();
        LocalDate initialPeriodToQuery = finalPeriodToQuery.minusYears(1);

        Long userId = userHandler.getCurrentUser().getId();

        if(initialPeriod != null && finalPeriod != null) {
            initialPeriodToQuery = LocalDate.parse(initialPeriod);
            finalPeriodToQuery = LocalDate.parse(finalPeriod);
        }

        return spendingRepository.getMoreExpansiveTags(initialPeriodToQuery, finalPeriodToQuery, userId);}
}
