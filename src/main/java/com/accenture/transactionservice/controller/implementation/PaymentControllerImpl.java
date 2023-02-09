package com.accenture.transactionservice.controller.implementation;

import com.accenture.transactionservice.controller.PaymentController;
import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import com.accenture.transactionservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentControllerImpl implements PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Override
    public ResponseEntity<ErrorResponse> createPaymentMethod(PaymentMethodDTO newPaymentMethod) {
        return null;
    }

    @Override
    public ResponseEntity<ErrorResponse> getPaymentMethod(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ErrorResponse> existPaymentMethod(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ErrorResponse> listPaymentMethod() {
        return null;
    }
}
