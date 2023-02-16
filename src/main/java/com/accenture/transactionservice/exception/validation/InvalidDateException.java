package com.accenture.transactionservice.exception.validation;

public class InvalidDateException extends ValidationException {

    private static Integer CODE = 7;

    private static String INVALID_DATE_MSG = "La fecha es invalida";

    public InvalidDateException() {super(INVALID_DATE_MSG, CODE);}

}
