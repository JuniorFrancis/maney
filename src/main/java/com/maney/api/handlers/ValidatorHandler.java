package com.maney.api.handlers;

import com.maney.api.exceptions.DefaultException;
import com.maney.api.model.Card;

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
             throw new DefaultException("nulo");
         }
    }

    public static void checkNotEmpty(String value) {
        if(value.trim().isEmpty()) {
            throw new DefaultException("campo vazio");
        }
    }

    public static void checkIsNotZero(Integer value) {
        if(value <= 0) {
            throw new DefaultException("");
        }
    }

    public static void checkIsNotZero(Long value) {
        if(value <= 0) {
            throw new DefaultException("");
        }
    }

}