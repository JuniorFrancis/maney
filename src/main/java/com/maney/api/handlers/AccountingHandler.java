package com.maney.api.handlers;

import com.maney.api.model.Revenue;
import com.maney.api.model.Spending;
import com.maney.api.model.responses.AccountingResponse;

import java.time.LocalDate;
import java.util.List;

public class AccountingHandler {

    public static AccountingResponse toAccountingResponse(List<Spending> spending, List<Revenue> revenues, LocalDate period){
        int totalSpentAmount;
        int totalReceivedAmount;

        totalSpentAmount = spending.stream().mapToInt(Spending::getAmount).sum();
        totalReceivedAmount = revenues.stream().mapToInt(Revenue::getAmount).sum();

        return new AccountingResponse.Builder()
                .withTotalSpentAmount(totalSpentAmount)
                .withTotalReceivedValue(totalReceivedAmount)
                .withReferenceMonth(period.getMonth())
                .build();

    }

}
