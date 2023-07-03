package com.maney.api.services.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.models.Card;
import com.maney.api.models.Revenue;
import com.maney.api.models.Spending;
import com.maney.api.models.responses.AccountingResponse;
import com.maney.api.repositories.SpendingRepository;
import com.maney.api.repositories.projects.ProjectTagAndAmount;
import com.maney.api.services.AccountingService;
import com.maney.api.handlers.DateHandler;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.maney.api.handlers.AccountingHandler.toAccountingResponse;
import static com.maney.api.handlers.ValidatorHandler.isValidDate;

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

    public AccountingResponse overview(String dateToQuery) {
        Long userId = userHandler.getCurrentUser().getId();
        isValidDate(dateToQuery);

        LocalDate date = LocalDate.parse(dateToQuery);

        ArrayList<Spending> spending = new ArrayList<>();
        List<Revenue> revenues = revenueService.getRevenues();
        Map<Card, List<LocalDate>> periods = dateHandler.parsePeriodToAllCards(date);

        periods.forEach( (card, dates) -> {
            List<Spending> currentSpending = spendingRepository.findByDateSpendingBetweenAndCardAndUserId(dates.get(0), dates.get(1), card, userId);
            spending.addAll(currentSpending);
        });

        return toAccountingResponse(spending, revenues, date);
    }

    public List<ProjectTagAndAmount> expansiveTagsByPeriod(@Nullable String initialPeriod, @Nullable String finalPeriod){

        LocalDate finalPeriodToQuery = LocalDate.now();
        LocalDate initialPeriodToQuery = finalPeriodToQuery.minusYears(1);

        Long userId = userHandler.getCurrentUserId();

        if(initialPeriod != null && finalPeriod != null) {
            initialPeriodToQuery = LocalDate.parse(initialPeriod);
            finalPeriodToQuery = LocalDate.parse(finalPeriod);
        }

        return spendingRepository.getMoreExpansiveTagsByPeriod(initialPeriodToQuery, finalPeriodToQuery, userId);}

    public List<ProjectTagAndAmount> expansiveTags() {
        Long userId = userHandler.getCurrentUserId();
        return spendingRepository.getMoreExpansiveTags(userId);
    }
}
