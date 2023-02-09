package com.accenture.transactionservice.operation;

import com.accenture.transactionservice.exception.TransactionDAOException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.TransactionDTO;

public interface PaymentOperation {

    TransactionDTO execute(TransactionDTO transaction) throws TransactionServiceException, TransactionDAOException;

}
