package com.spring.calculadoras.ExceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class IngredientNotFoundException extends Exception{
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
