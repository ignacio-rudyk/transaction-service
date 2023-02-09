package com.accenture.transactionservice.exception;

public class TransactionServiceException extends Exception {

    private Integer code;

    private static String TRANSACTION_SERVICE_EXCEPTION = "Hubo un error en el servicio";

    public TransactionServiceException() {
        this(TRANSACTION_SERVICE_EXCEPTION, -1);
    }

    public TransactionServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
