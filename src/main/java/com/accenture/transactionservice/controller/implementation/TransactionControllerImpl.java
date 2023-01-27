package com.accenture.transactionservice.controller.implementation;

import com.accenture.transactionservice.controller.TransactionController;
import com.accenture.transactionservice.dao.TransactionDAO;
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
    public ResponseEntity<TransactionDTO> createTransaction(TransactionDTO newTransaction) {
        TransactionDTO transaction =transactionService.saveTransaction(newTransaction);
        return new ResponseEntity<TransactionDTO>(transaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionDTO> getTransaction(Long id) {
        TransactionDTO transaction = transactionService.findById(id);
        return new ResponseEntity<TransactionDTO>(transaction,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> existTransaction(Long id) {
        Boolean result = transactionService.existsById(id);
        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<List<TransactionDTO>> list() {
        List<TransactionDTO> result = transactionService.list();
        ResponseEntity<List<TransactionDTO>> response = new ResponseEntity<List<TransactionDTO>>(result, HttpStatus.OK);
        return response;
    }
}
