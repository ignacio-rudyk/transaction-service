package com.accenture.transactionservice.service;

import com.accenture.transactionservice.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO saveTransaction(TransactionDTO newTransaction);

    TransactionDTO findById(Long id);

    Boolean existsById(Long id);

    List<TransactionDTO> list();

}
