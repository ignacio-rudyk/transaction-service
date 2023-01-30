package com.accenture.transactionservice.controller;

import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.PayDTO;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface PaymentController {

    @PostMapping("/pay")
    ResponseEntity<ErrorResponse> pay(@RequestBody PayDTO pay);

    @PostMapping(value = "/createPaymentMethod")
    ResponseEntity<ErrorResponse> createPaymentMethod(@RequestBody PaymentMethodDTO newPaymentMethod);

    @GetMapping(value = "/getPaymentMethod/{id}")
    ResponseEntity<ErrorResponse> getPaymentMethod(@PathVariable(name="id") Long id);

    @GetMapping(value = "/existPaymentMethod/{id}")
    ResponseEntity<ErrorResponse> existPaymentMethod(@PathVariable(name="id") Long id);

    @PostMapping(value = "/list")
    ResponseEntity<ErrorResponse> listPaymentMethod();
}
