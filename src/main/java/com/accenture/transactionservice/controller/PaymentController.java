package com.accenture.transactionservice.controller;

import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/payment")
public interface PaymentController {

    @PostMapping(value = "/createPaymentMethod")
    ResponseEntity<ErrorResponse> createPaymentMethod(@RequestBody PaymentMethodDTO newPaymentMethod);

    @GetMapping(value = "/getPaymentMethod/{id}")
    ResponseEntity<ErrorResponse> getPaymentMethod(@PathVariable(name="id") Long id);

    @GetMapping(value = "/existPaymentMethod/{id}")
    ResponseEntity<ErrorResponse> existPaymentMethod(@PathVariable(name="id") Long id);

    @PostMapping(value = "/list")
    ResponseEntity<ErrorResponse> listPaymentMethod();
}
