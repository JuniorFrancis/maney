package com.maney.api.service;

import com.maney.api.model.responses.AccountingResponse;

import java.time.LocalDate;

public interface AccountingService {

    AccountingResponse overview(LocalDate dateToQuery);

}
