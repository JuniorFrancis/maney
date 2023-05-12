package com.maney.api.utils;

import com.maney.api.model.Revenue;
import com.maney.api.model.Spending;
import com.maney.api.model.responses.AccountingResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BinaryOperator;

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
