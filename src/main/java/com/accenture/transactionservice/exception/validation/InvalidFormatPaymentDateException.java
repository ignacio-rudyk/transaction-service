package com.accenture.transactionservice.exception.validation;

public class InvalidFormatPaymentDateException extends ValidationException {

    private static Integer CODE = 4;
    private static String INVALID_FORMAT_PAYMENT_EXCEPTION_MSG = "El formato de la fecha del pago es invalida. La fecha debe cumplir con el formato DDMMAAAA";
    public InvalidFormatPaymentDateException() {
        super(INVALID_FORMAT_PAYMENT_EXCEPTION_MSG, CODE);
    }
}
