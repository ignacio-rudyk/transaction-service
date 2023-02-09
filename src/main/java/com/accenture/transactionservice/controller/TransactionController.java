package com.accenture.transactionservice.controller;

import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/transaction")
public interface TransactionController {

    @PostMapping("/generatePay")
    ResponseEntity<ErrorResponse> generatePay(@RequestBody TransactionDTO newTransaction);

    @GetMapping("/getTransaction/{id}")
    ResponseEntity<ErrorResponse> getTransaction(@PathVariable(name="id") Long id);

    @GetMapping("/getTransactionBetweenDates")
    ResponseEntity<ErrorResponse> getTransactionBetweenDates(@RequestParam(name = "fromDate") String fromDate, @RequestParam(name = "toDate") String toDate);


    @GetMapping("/existTransaction/{id}")
    ResponseEntity<ErrorResponse> existTransaction(@PathVariable(name="id") Long id);

    @GetMapping("/list")
    ResponseEntity<ErrorResponse> list();

}