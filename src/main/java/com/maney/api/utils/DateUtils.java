package com.maney.api.utils;

import com.maney.api.constants.Brand;
import com.maney.api.model.Card;

import java.time.LocalDate;
import java.util.List;

public class DateUtils {

    public static List<LocalDate> parsePeriod(LocalDate rawDate, Card card, Integer daysToCloseCard){
        //TODO Montar Strateg Pattern to chose the logic to less days
        Integer dueDay = card.getDueDay();

        LocalDate initialDate = rawDate.withDayOfMonth(dueDay).minusDays(daysToCloseCard);
        LocalDate finalDate;

        if(card.getBrand().equals(Brand.VISA)) {
            finalDate = initialDate.plusMonths(1);
        } else {
            finalDate = initialDate.minusMonths(1);
        }

        return List.of(initialDate, finalDate);
    }

}
