package com.maney.api.utils;

import com.maney.api.constants.Brand;
import com.maney.api.model.Card;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DateUtils {

    public static List<LocalDate> parsePeriod(LocalDate rawDate, Card card, Integer daysToCloseCard){
        //TODO Montar Strateg Pattern to chose the logic to less days
        //TODO Transformar isso em annotation para que possamos passar o daysToCloseCard diretamente aqui.
        Integer dueDay = card.getDueDay();

        LocalDate initialDate = rawDate.withDayOfMonth(dueDay).minusDays(daysToCloseCard);
        LocalDate finalDate;

        if(card.getBrand() != null && card.getBrand().equals(Brand.VISA)) {
            finalDate = initialDate.plusMonths(1);
        } else {
            finalDate = initialDate.minusMonths(1);
        }

        ArrayList<LocalDate> period = new ArrayList<>();

        period.add(initialDate);
        period.add(finalDate);
        Collections.sort(period);

        return period;
    }

}
