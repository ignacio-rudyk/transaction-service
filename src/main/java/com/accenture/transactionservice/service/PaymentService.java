package com.accenture.transactionservice.service;

import com.accenture.transactionservice.exception.validation.InvalidAccountIdException;
import com.accenture.transactionservice.exception.validation.ValidationException;
import com.accenture.transactionservice.model.dto.PayDTO;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.model.entities.PaymentMethod;

import java.util.List;

public interface PaymentService {

    PaymentMethodDTO generatePay(PayDTO newPaymentMethod) throws ValidationException;

    PaymentMethodDTO createPaymentMethod(PaymentMethodDTO newPaymentMethod);

    PaymentMethodDTO findPaymentMethodById(Long id);

    Boolean existPaymentMethodById(Long id);

    List<PaymentMethodDTO> listPaymentMethod();

}
