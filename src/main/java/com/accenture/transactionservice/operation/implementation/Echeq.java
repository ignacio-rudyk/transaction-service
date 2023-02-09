package com.accenture.transactionservice.operation.implementation;

import com.accenture.transactionservice.exception.TransactionDAOException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.operation.PaymentOperation;

public class Echeq implements PaymentOperation {
    @Override
    public TransactionDTO execute(TransactionDTO transaction) throws TransactionServiceException, TransactionDAOException {
        return null;
    }
}
