package com.maney.api.handlers;

import com.maney.api.models.Revenue;
import com.maney.api.models.Spending;
import com.maney.api.models.responses.AccountingResponse;
import com.maney.api.models.responses.SpendingResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ObjectMapperHandler {

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


    public static SpendingResponse toSpendingResponse(List<Page> spending){
        int totalPages;
        long totalElements;
        ArrayList<Spending> content = new ArrayList<>();

        totalElements = spending.stream().mapToLong(Page::getTotalElements).sum();
        totalPages = spending.stream().mapToInt(Page::getTotalPages).sum();

        spending.forEach( spent -> {
            content.addAll(spent.getContent());
        });

        return new SpendingResponse( content, totalPages, totalElements);
    }

}
