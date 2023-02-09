package com.accenture.transactionservice.exception;

public class TransactionInexistentException extends TransactionServiceException {

    private static Integer CODE = -4;

    private static String TRANSACTION_INEXISTENT_MSG = "La transacción solicitada no existe";

    public TransactionInexistentException() {
        super(TRANSACTION_INEXISTENT_MSG, CODE);
    }

}
