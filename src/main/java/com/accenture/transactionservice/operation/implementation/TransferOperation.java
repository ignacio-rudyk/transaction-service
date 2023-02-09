package com.accenture.transactionservice.operation.implementation;

import com.accenture.transactionservice.dao.TransactionDAO;
import com.accenture.transactionservice.exception.TransactionDAOException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.SendingOfMoneyDTO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.model.dto.WithdrawalOfMoneyDTO;
import com.accenture.transactionservice.model.entities.Transaction;
import com.accenture.transactionservice.operation.PaymentOperation;
import com.accenture.transactionservice.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component("transferOperation")
public class TransferOperation implements PaymentOperation {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public TransactionDTO execute(TransactionDTO transaction) throws TransactionServiceException, TransactionDAOException {
        try {
            WithdrawalOfMoneyDTO amountSubtractedFromUser = new WithdrawalOfMoneyDTO(transaction.getSourceAccountNumber(), transaction.getAmount().negate().toPlainString());
            accountService.subtractAmount(amountSubtractedFromUser);
            SendingOfMoneyDTO sendingOfMoney = new SendingOfMoneyDTO(transaction.getCbuDestination(), transaction.getAmount().toPlainString());
            accountService.addAmount(sendingOfMoney);
            Transaction successfulTransaction = transactionDAO.save(mapper.map(transaction, Transaction.class));
            return mapper.map(successfulTransaction, TransactionDTO.class);
        } catch (DataAccessException e) {
            throw new TransactionDAOException();
        } catch (TransactionServiceException e) {
            throw e;
        } catch (Throwable t) {
            throw t;
        }
    }

}
