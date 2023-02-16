package com.accenture.transactionservice.exception.validation;

public class InvalidDateSizeException extends ValidationException{

    private static Integer CODE = 5;

    private static String INVALID_DATE_SIZE_MSG = "La longitud de la fecha es invalida";

    public InvalidDateSizeException() {super(INVALID_DATE_SIZE_MSG, CODE);}

}
