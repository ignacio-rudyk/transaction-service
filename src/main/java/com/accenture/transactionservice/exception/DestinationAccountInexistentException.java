package com.accenture.transactionservice.exception;

public class DestinationAccountInexistentException extends TransactionServiceException {

    private static Integer CODE = -3;

    private static String DESTINATION_ACCOUNT_INEXISTENT_MSG = "El CBU de destino es inexistente";

    public DestinationAccountInexistentException() {
        super(DESTINATION_ACCOUNT_INEXISTENT_MSG, CODE);
    }
}
