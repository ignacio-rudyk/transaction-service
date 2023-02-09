package com.accenture.transactionservice.service.implementation;

import com.accenture.transactionservice.dao.TransactionDAO;
import com.accenture.transactionservice.exception.*;
import com.accenture.transactionservice.exception.validation.*;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.model.entities.Transaction;
import com.accenture.transactionservice.operation.PaymentOperation;
import com.accenture.transactionservice.service.AccountService;
import com.accenture.transactionservice.service.PaymentService;
import com.accenture.transactionservice.service.TransactionService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ApplicationContext contextApplication;

    private static String PATTERN_NUMBER_ACCOUNT = "00[0-9]{8}";

    private static String PATTERN_DATE = "([0-2][0-9]|3[0-1])(0[1-9]|1[0-2])([0-9][0-9][0-9][0-9])";

    private static String PATTERN_AMOUNT_TRANSACTION = "[0-9]+\\.[0-9]{2}";

    private static String DATE_FORMAT = "ddMMyyyy";



    @Override
    public TransactionDTO generatePay(TransactionDTO newTransaction) throws TransactionServiceException, TransactionDAOException {
        try {
            validateTransaction(newTransaction);
            validateAccounts(newTransaction.getSourceAccountNumber(), newTransaction.getCbuDestination());
            PaymentMethodDTO paymentMethod = paymentService.findPaymentMethodById(newTransaction.getPaymentMethodId());
            PaymentOperation paymentOperation = (PaymentOperation) contextApplication.getBean(paymentMethod.getBeanPaymentOperation());
            return paymentOperation.execute(newTransaction);
        } catch (TransactionDAOException e) {
            throw e;
        } catch (TransactionServiceException e) {
            throw e;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public TransactionDTO findById(Long id) throws TransactionServiceException {
        if(id == null) {
            throw new FieldNullException();
        }
        Optional<Transaction> result = transactionDAO.findById(id);
        if(!result.isEmpty()){
            return mapper.map(result.get(), TransactionDTO.class);
        } else {
            throw new TransactionInexistentException();
        }
    }

    @Override
    public Boolean existsById(Long id) throws TransactionServiceException {
        if(id == null) {
            throw new FieldNullException();
        }
        return transactionDAO.existsById(id);
    }

    @Override
    public List<TransactionDTO> list() {
        List<Transaction> list = (List<Transaction>) transactionDAO.findAll();
        return list.stream()
                .map(e -> mapper.map(e, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionBetweenDates(String fromDateStr, String toDateStr) throws TransactionServiceException {
        if(fromDateStr == null || toDateStr == null) {
            throw new FieldNullException();
        }
        if(fromDateStr.length() != 8 || toDateStr.length() != 8 ){
            throw new InvalidDateRangeException();
        }
        try{
            List<Transaction> list = (List<Transaction>) transactionDAO.findAll();
            return list.stream()
                    .map(e -> mapper.map(e, TransactionDTO.class))
                    .filter(e -> isValidDate(e.getDatePayment(), fromDateStr, toDateStr))
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            throw t;
        }
    }

    private void validateTransaction(TransactionDTO transaction) throws ValidationException {
        if(transaction.getAmount() == null || transaction.getDatePayment() == null || transaction.getPaymentMethodId() == null
                || transaction.getCbuDestination() == null || transaction.getSourceAccountNumber() == null) {
            throw new FieldNullException();
        }
        if(!transaction.getSourceAccountNumber().matches(PATTERN_NUMBER_ACCOUNT)){
            throw new InvalidAccountIdException();
        }
        BigDecimal numberZero = new BigDecimal(0);
        if(!transaction.getAmount().toPlainString().matches(PATTERN_AMOUNT_TRANSACTION) || transaction.getAmount().compareTo(numberZero) <= 0) {
            throw new InvalidAmountException();
        }
        if(transaction.getDatePayment().length() != 8 || !transaction.getDatePayment().matches(PATTERN_DATE)) {
            throw new InvalidFormatPaymentDateException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate paymentDate = LocalDate.parse(transaction.getDatePayment(), formatter);
        LocalDate today = LocalDate.now();
        if(paymentDate.isBefore(today)) {
            throw new InvalidDateRangeException();
        }
    }

    private void validateAccounts(String sourceNumberAccount, String cbuDestination) throws TransactionServiceException {
        try {
            if(!accountService.existAccountByNumberAccount(sourceNumberAccount)) {
                throw new SourceAccountInexistentException();
            }
            if(!accountService.existAccountByCbu(cbuDestination)) {
                throw new DestinationAccountInexistentException();
            }
        } catch (TransactionServiceException e) {
            throw e;
        } catch (Throwable e) {
            throw e;
        }
    }

    private Boolean isValidDate(String dateTransaction, String fromDateStr, String toDateStr) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
            LocalDate toDate = LocalDate.parse(toDateStr, formatter);
            if((LocalDate.parse(dateTransaction, formatter)).equals(fromDate) || (LocalDate.parse(dateTransaction, formatter)).equals(toDate)) {
                return Boolean.TRUE;
            }
            if((LocalDate.parse(dateTransaction, formatter)).isAfter(fromDate) && (LocalDate.parse(dateTransaction, formatter)).isBefore(toDate)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Throwable t) {
            throw t;
        }
    }

}
