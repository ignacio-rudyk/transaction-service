package com.accenture.transactionservice.exception;

public class TransactionDAOException extends Exception {

    private static String TRANSACTION_DAO_EXCEPTION = "Hubo un error al acceder a los datos";

    public TransactionDAOException() { this(TRANSACTION_DAO_EXCEPTION); }

    public TransactionDAOException(String msg) { super(msg); }

}
