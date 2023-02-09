package com.accenture.transactionservice.exception.validation;

public class InvalidAmountException extends ValidationException {

    private static Integer CODE = 3;

    private static String INVALID_AMOUNT_MSG = "El monto del pago es invalido";

    public InvalidAmountException() {
        super(INVALID_AMOUNT_MSG, CODE);
    }

}
