package com.accenture.transactionservice.exception.validation;

public class InvalidAccountIdException extends ValidationException {

    private static Integer CODE = 1;

    private static String INVALID_ACCOUNT_ID_MSG = "El ID del numero de cuenta es invalido";

    public InvalidAccountIdException() {
        super(INVALID_ACCOUNT_ID_MSG, CODE);
    }

}
