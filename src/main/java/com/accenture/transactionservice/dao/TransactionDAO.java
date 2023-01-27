package com.accenture.transactionservice.dao;

import com.accenture.transactionservice.model.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDAO extends CrudRepository <Transaction, Long> {
}
