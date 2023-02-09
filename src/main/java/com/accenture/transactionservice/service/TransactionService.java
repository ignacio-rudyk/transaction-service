package com.accenture.transactionservice.service;

import com.accenture.transactionservice.exception.TransactionDAOException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO generatePay(TransactionDTO newPaymentMethod) throws TransactionServiceException, TransactionDAOException;

    TransactionDTO findById(Long id) throws TransactionServiceException;

    Boolean existsById(Long id) throws TransactionServiceException;

    List<TransactionDTO> list();

    List<TransactionDTO> getTransactionBetweenDates(String fromDateStr, String toDateStr) throws TransactionServiceException;

}
