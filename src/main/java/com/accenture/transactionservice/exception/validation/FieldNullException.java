package com.accenture.transactionservice.exception.validation;

public class FieldNullException extends ValidationException {

    private static Integer CODE = 6;

    private static String FIELD_NULL_EXCEPTION = "Campo nulo";

    public FieldNullException() {
        super(FIELD_NULL_EXCEPTION, CODE);
    }
}
