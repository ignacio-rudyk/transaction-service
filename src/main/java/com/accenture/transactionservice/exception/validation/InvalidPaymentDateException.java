package com.accenture.transactionservice.exception.validation;

public class InvalidPaymentDateException extends ValidationException {

    private static Integer CODE = 3;
    private static String INVALID_PAYMENT_EXCEPTION_MSG = "La fecha del pago es invalida";
    public InvalidPaymentDateException() {
        super(INVALID_PAYMENT_EXCEPTION_MSG, CODE);
    }
}
