package com.accenture.transactionservice.service;

import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.MoneyOperationDTO;

public interface AccountService {

    MoneyOperationDTO addAmount(MoneyOperationDTO addingMoney) throws TransactionServiceException;

    MoneyOperationDTO subtractAmount(MoneyOperationDTO moneyTheft) throws TransactionServiceException;

    Long getAccountIdByCBU(String cbu) throws TransactionServiceException;

    Long getAccountIdByNumberAccount(String numberAccount) throws TransactionServiceException;

}
