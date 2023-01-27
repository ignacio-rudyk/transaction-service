package com.accenture.transactionservice.controller;

import com.accenture.transactionservice.model.dto.PayDTO;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface TransactionController {

    @PostMapping(value = "/createTransaction")
    ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO newTransaction);

    @GetMapping("/getTransaction/{id}")
    ResponseEntity<TransactionDTO> getTransaction(@PathVariable(name="id") Long id);

    @GetMapping("/existTransaction/{id}")
    ResponseEntity<Boolean> existTransaction(@PathVariable(name="id") Long id);

    @GetMapping("/list")
    ResponseEntity<List<TransactionDTO>> list();

    /*@PostMapping("/pay")
    ResponseEntity<List<TransactionDTO>> pay(@RequestBody PayDTO pay);

    @PostMapping(value = "/createPaymentMethod")
    ResponseEntity<PaymentMethodDTO> createTransaction(@RequestBody PaymentMethodDTO newPaymentMethod);*/

}