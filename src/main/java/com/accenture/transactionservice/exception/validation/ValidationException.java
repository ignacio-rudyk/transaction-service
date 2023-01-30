package com.accenture.transactionservice.exception.validation;

import com.accenture.transactionservice.exception.TransactionServiceException;

public class ValidationException extends TransactionServiceException {

    public ValidationException(Integer code) {
        super(code);
    }

    public ValidationException(String message, Integer code) {
        super(message, code);
    }

}
