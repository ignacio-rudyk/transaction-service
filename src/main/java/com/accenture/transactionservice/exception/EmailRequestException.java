package com.accenture.transactionservice.exception;

public class EmailRequestException extends TransactionServiceException{

    private static Integer CODE = -5;

    private static String EMAIL_REQUEST_MSG = "Hubo un error al solicitar enviar un email";

    public EmailRequestException(){ super(EMAIL_REQUEST_MSG, CODE); }

}
