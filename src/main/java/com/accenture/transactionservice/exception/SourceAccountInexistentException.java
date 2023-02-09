package com.accenture.transactionservice.exception;

public class SourceAccountInexistentException extends TransactionServiceException{

    private static Integer CODE = -2;

    private static String SOURCE_ACCOUNT_INEXISTENT_MSG = "El número de cuenta de origen es inexistente";

    public SourceAccountInexistentException() {
        super(SOURCE_ACCOUNT_INEXISTENT_MSG, CODE);
    }

}
