package com.thomasaleknovic.workestimateapi.exceptions.User;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Não foi possível encontrar o usuário.");
    }
}
