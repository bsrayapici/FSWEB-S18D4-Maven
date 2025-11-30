package com.workintech.s18d1.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BurgerErrorResponse {

    private String message;


    public BurgerErrorResponse(String message) {
        this.message = message;
    }
}