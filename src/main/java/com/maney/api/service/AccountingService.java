package com.maney.api.service;

import com.maney.api.model.responses.AccountingResponse;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public interface AccountingService {

    AccountingResponse overview(LocalDate dateToQuery);

    AccountingResponse expansiveTags(@Nullable String initialPeriod, @Nullable String finalPeriod);

}
