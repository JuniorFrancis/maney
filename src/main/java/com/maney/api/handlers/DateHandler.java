package com.maney.api.handlers;

import com.maney.api.constants.Brand;
import com.maney.api.model.Card;
import com.maney.api.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class DateHandler {

    @Autowired
    CardRepository cardRepository;

    @Value("${card.days_to_close_card}")
    private final Integer DAYS_TO_CLOSE_CARD = 7;

    public List<LocalDate> parsePeriod(LocalDate rawDate, Card card){
        //TODO Montar Strateg Pattern to chose the logic to less days
        //TODO Transformar isso em annotation para que possamos passar o daysToCloseCard diretamente aqui.
        Integer dueDay = card.getDueDay();

        LocalDate initialDate = rawDate.withDayOfMonth(dueDay).minusDays(DAYS_TO_CLOSE_CARD);
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

    public Map<Card, List<LocalDate>> parsePeriodToAllCards(LocalDate rawDate){

        List<Card> cards = cardRepository.findAll();

        Map<Card, List<LocalDate>> periodsByCard = new HashMap<>(Map.of());

        cards.forEach( card -> {

            List<LocalDate> period = this.parsePeriod(rawDate, card);

            periodsByCard.put(card, period);

        });

        return periodsByCard;

    }

}
