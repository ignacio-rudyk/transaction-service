package com.accenture.transactionservice.exception;

public class TransactionServiceException extends Exception {

    private Integer code;

    public TransactionServiceException(Integer code) {
        this.code = code;
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
