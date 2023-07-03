package com.maney.api.services;

import com.maney.api.models.Spending;

import java.time.LocalDate;
import java.util.List;

public interface SpendingService {

    void register(List<Spending> spending);

    List<Spending> getSpending();

    Spending getSpent(Long id);

    List<Spending> byPeriod(LocalDate period, List<String> brand);

}
