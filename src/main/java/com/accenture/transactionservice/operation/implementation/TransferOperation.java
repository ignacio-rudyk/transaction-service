package com.accenture.transactionservice.operation.implementation;

import com.accenture.transactionservice.dao.TransactionDAO;
import com.accenture.transactionservice.exception.EmailRequestException;
import com.accenture.transactionservice.exception.TransactionDAOException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.dto.MailTemplateDTO;
import com.accenture.transactionservice.model.dto.MoneyOperationDTO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.model.entities.Transaction;
import com.accenture.transactionservice.operation.PaymentOperation;
import com.accenture.transactionservice.service.AccountService;
import com.google.gson.Gson;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
* Esta clase define la operación para realizar transferencias de pago
* entre 2 cuentas bancarias. Al completar su tarea envía un email de
* notificación a quien haya ejecutado la operacion.
*/

@Component("transferOperation")
public class TransferOperation implements PaymentOperation {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private Mapper mapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Gson gson;

    private final Logger logger = LoggerFactory.getLogger(TransferOperation.class);

    @Value("${queue.email.notification.transaction}")
    private String queueEmailNotificationTransactionDestiny;

    private static final String DATE_FORMAT = "ddMMyyyy";

    @Override
    public TransactionDTO execute(TransactionDTO pendingTransaction) throws TransactionServiceException, TransactionDAOException {
        logger.info("[Method: execute] " + pendingTransaction);
        try {
            Long accountIdSource = accountService.getAccountIdByNumberAccount(pendingTransaction.getSourceAccountNumber());
            Long accountIdDestination = accountService.getAccountIdByCBU(pendingTransaction.getCbuDestination());
            MoneyOperationDTO amountSubtractedFromUser = new MoneyOperationDTO(accountIdSource, pendingTransaction.getAmount().negate().toPlainString());
            accountService.subtractAmount(amountSubtractedFromUser);
            MoneyOperationDTO sendingOfMoney = new MoneyOperationDTO(accountIdDestination, pendingTransaction.getAmount().toPlainString());
            accountService.addAmount(sendingOfMoney);
            saveTransaction(pendingTransaction, accountIdSource, accountIdDestination);
            sendMailNotification(pendingTransaction);
            return pendingTransaction;
        } catch (DataAccessException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw new TransactionDAOException();
        } catch (EmailRequestException e) {
            return pendingTransaction;
        } catch (TransactionServiceException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw e;
        } catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            throw t;
        }
    }

    private TransactionDTO saveTransaction(TransactionDTO pendingTransactionSave, Long accountIdSource, Long accountIdDestination) {
        Transaction transaction = mapper.map(pendingTransactionSave, Transaction.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        transaction.setDatePayment(LocalDate.now().format(formatter));
        transaction.setSourceAccountId(accountIdSource);
        transaction.setDestinationAccountId(accountIdDestination);
        return mapper.map(transactionDAO.save(transaction), TransactionDTO.class);
    }

    /*El hardcode es a modo de demostracion para Active MQ*/
    private void sendMailNotification(TransactionDTO transaction) throws TransactionServiceException {
        try{
            MailTemplateDTO mailTemplate = new MailTemplateDTO();
            mailTemplate.setBody("Su transaccion ha sido existosa! Le ha transferido $" + transaction.getAmount().toPlainString());
            mailTemplate.setSubject("Successful Transaction");
            mailTemplate.setTo("ignacio.rudyk@gmail.com");
            jmsTemplate.convertAndSend(queueEmailNotificationTransactionDestiny, gson.toJson(mailTemplate));
        } catch (JmsException e) {
            logger.error("[Error " + e.getClass() + "] " + e.getMessage());
            throw new EmailRequestException();
        }catch (Throwable t) {
            logger.error("[Error " + t.getClass() + "] " + t.getMessage());
            throw t;
        }
    }

}
