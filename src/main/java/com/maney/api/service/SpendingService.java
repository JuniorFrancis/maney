package com.maney.api.service;

import com.maney.api.model.Spending;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpendingService {

    void register(Spending spending);

    List<Spending> getSpending();

    Optional<Spending> getSpent(Long id);

    List<Spending> byPeriod(LocalDate period, List<String> brand);

}
