package com.thomasaleknovic.workestimateapi.exceptions.User;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("Nome de Usuário já cadastrado.");
    }
}
