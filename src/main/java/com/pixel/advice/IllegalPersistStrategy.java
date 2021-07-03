package com.pixel.advice;

/**
 * Created by Maciej Muzyka
 * on 03.07.2021
 */

public class IllegalPersistStrategy extends RuntimeException{
    public IllegalPersistStrategy(final String message) {
        super(message);
    }
}
