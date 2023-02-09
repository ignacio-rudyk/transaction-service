package com.accenture.transactionservice.controller.implementation;

import com.accenture.transactionservice.controller.TransactionController;
import com.accenture.transactionservice.exception.TransactionInexistentException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.exception.validation.ValidationException;
import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionService transactionService;

    private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Override
    public ResponseEntity<ErrorResponse> generatePay(TransactionDTO newTransaction) {
        logger.info("[Microservice:Transaction, Endpoint:generatePay] new_transaction = " + newTransaction);
        try{
            TransactionDTO generatedTransaction = transactionService.generatePay(newTransaction);
            ErrorResponse errorResponse = new ErrorResponse(generatedTransaction);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (TransactionServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> getTransaction(Long id) {
        logger.info("[Microservice:Transaction, Endpoint:getTransaction] id = " + id);
        try {
            TransactionDTO transactionFound = transactionService.findById(id);
            ErrorResponse errorResponse = new ErrorResponse(transactionFound);
            return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
        } catch (TransactionInexistentException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.NO_CONTENT);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (TransactionServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> getTransactionBetweenDates(String fromDate, String toDate) {
        logger.info("[Microservice:Transaction, Endpoint:getTransactionBetweenDates] fromDate = " + fromDate + " toDate " + toDate);
        try{
            List<TransactionDTO> transactionList = transactionService.getTransactionBetweenDates(fromDate, toDate);
            ErrorResponse errorResponse = new ErrorResponse(transactionList);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (TransactionServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t){
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> existTransaction(Long id) {
        logger.info("[Microservice:Transaction, Endpoint:existTransaction] id = " + id);
        try {
            Boolean exist = transactionService.existsById(id);
            ErrorResponse errorResponse = new ErrorResponse(exist);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (TransactionServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> list() {
        logger.info("[Microservice:Transaction, Endpoint:list]");
        try{
            List<TransactionDTO> transactionList = transactionService.list();
            ErrorResponse errorResponse = new ErrorResponse(transactionList);
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(null, t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
