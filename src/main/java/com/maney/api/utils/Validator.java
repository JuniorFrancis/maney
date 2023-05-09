package com.maney.api.utils;

import com.maney.api.exceptions.DefaultException;
import com.maney.api.model.Card;

import java.util.Optional;

public class Validator {

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

}
