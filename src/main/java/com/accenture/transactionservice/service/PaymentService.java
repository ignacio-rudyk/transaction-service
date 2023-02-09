package com.accenture.transactionservice.service;

import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;

import java.util.List;

public interface PaymentService {

    PaymentMethodDTO createPaymentMethod(PaymentMethodDTO newPaymentMethod);

    PaymentMethodDTO findPaymentMethodById(Long id) throws TransactionServiceException;

    Boolean existPaymentMethodById(Long id);

    List<PaymentMethodDTO> listPaymentMethod();

}
