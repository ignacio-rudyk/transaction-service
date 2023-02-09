package com.accenture.transactionservice.service;

import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.SendingOfMoneyDTO;
import com.accenture.transactionservice.model.dto.WithdrawalOfMoneyDTO;

public interface AccountService {

    SendingOfMoneyDTO addAmount(SendingOfMoneyDTO sendingOfMoney) throws TransactionServiceException;

    WithdrawalOfMoneyDTO subtractAmount(WithdrawalOfMoneyDTO withdrawalOfMoney) throws TransactionServiceException;

    Boolean existAccountByNumberAccount(String numberAccount) throws TransactionServiceException;

    Boolean existAccountByCbu(String cbu) throws TransactionServiceException;

}
