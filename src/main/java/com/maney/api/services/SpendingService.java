package com.maney.api.services;

import com.maney.api.models.Spending;
import com.maney.api.models.responses.SpendingResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface SpendingService {

    void register(List<Spending> spending);

    Page<Spending> getSpending(int page, int size);

    Spending getSpent(Long id);

    SpendingResponse byPeriod(LocalDate period, List<String> brand, int page, int size);

}
