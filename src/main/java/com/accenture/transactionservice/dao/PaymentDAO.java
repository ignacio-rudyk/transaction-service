package com.accenture.transactionservice.dao;

import com.accenture.transactionservice.model.entities.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDAO extends CrudRepository<PaymentMethod, Long> {
}
