package com.accenture.transactionservice.controller.implementation;

import com.accenture.transactionservice.controller.TransactionController;
import com.accenture.transactionservice.dao.TransactionDAO;
import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Override
    public ResponseEntity<ErrorResponse> createTransaction(TransactionDTO newTransaction) {
        TransactionDTO transaction = transactionService.saveTransaction(newTransaction);
        ErrorResponse errorResponse = new ErrorResponse(transaction);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorResponse> getTransaction(Long id) {
        TransactionDTO transaction = transactionService.findById(id);
        ErrorResponse errorResponse = new ErrorResponse(transaction);
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorResponse> existTransaction(Long id) {
        Boolean result = transactionService.existsById(id);
        ErrorResponse errorResponse = new ErrorResponse(result);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorResponse> list() {
        List<TransactionDTO> result = transactionService.list();
        ErrorResponse errorResponse = new ErrorResponse(result);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
    }
}
