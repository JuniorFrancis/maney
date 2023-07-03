package com.maney.api.handlers;

import com.maney.api.exceptions.DefaultException;
import com.maney.api.models.Card;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ValidatorHandler {

    public static void isCardPresent(Card card ){
        if(card == null) {
            throw new DefaultException("Cartão inválido");
        }
    }

    public static void isCardPresent(Optional<Card> card ){
        if(card.isEmpty()) {
            throw new DefaultException("Cartão inválido");
        }
    }

    public static void isCardPresent(Optional<Card> card, String message ){
        if(card.isEmpty()) {
            throw new DefaultException(message);
        }
    }

    public static <T> void checkNotNull(T reference) {
         if(reference == null) {
             throw new IllegalArgumentException("Parameter is null");
         }
    }

    public static void isValidDate(String dateString){
        checkNotNull(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date to query");
        }
    }

    public static void checkNotEmpty(String value) {
        if(value.trim().isEmpty()) {
            throw new DefaultException("campo vazio");
        }
    }

    public static void checkIsNotZero(Integer value) {
        if(value <= 0) {
            throw new IllegalArgumentException("Amount can not be zero");
        }
    }

    public static void validateRevenueAmount(Integer amount){
        checkIsNotZero(amount);
        checkNotNull(amount);
    }

    public static void checkIsNotZero(Long value) {
        if(value <= 0) {
            throw new DefaultException("");
        }
    }

}
