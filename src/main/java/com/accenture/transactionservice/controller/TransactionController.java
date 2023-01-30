package com.accenture.transactionservice.controller;

import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface TransactionController {

    @PostMapping(value = "/createTransaction")
    ResponseEntity<ErrorResponse> createTransaction(@RequestBody TransactionDTO newTransaction);

    @GetMapping("/getTransaction/{id}")
    ResponseEntity<ErrorResponse> getTransaction(@PathVariable(name="id") Long id);

    @GetMapping("/existTransaction/{id}")
    ResponseEntity<ErrorResponse> existTransaction(@PathVariable(name="id") Long id);

    @GetMapping("/list")
    ResponseEntity<ErrorResponse> list();

}