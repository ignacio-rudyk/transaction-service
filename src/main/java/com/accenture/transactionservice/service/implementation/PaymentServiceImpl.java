package com.accenture.transactionservice.service.implementation;

import com.accenture.transactionservice.dao.PaymentDAO;
import com.accenture.transactionservice.exception.validation.*;
import com.accenture.transactionservice.model.dto.PayDTO;
import com.accenture.transactionservice.model.dto.PaymentMethodDTO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private static String PATTERN_NUMBER_ACCOUNT = "00[0-9]{8}";

    private static String DATE_FORMAT = "ddMMyyyy";
    private static String DATE_PATTERN = "([0-2][0-9]|3[0-1])(0[1-9]|1[0-2])([0-9][0-9][0-9][0-9])";

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public TransactionDTO generatePay(PayDTO newPayment) throws ValidationException {
        validatePayment(newPayment);
        return null;
    }

    private static void validatePayment(PayDTO newPayment) throws ValidationException {
        if(!newPayment.getNumberAccount().matches(PATTERN_NUMBER_ACCOUNT)){
            throw new InvalidAccountIdException();
        }
        BigDecimal numberZero = new BigDecimal(0);
        if(newPayment.getAmount().compareTo(numberZero) <= 0){
            throw new InvalidAmountException();
        }
        if(newPayment.getDate().length() != 8 || !newPayment.getDate().matches(DATE_PATTERN)) {
            throw new InvalidPaymentDateException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate paymentDate = LocalDate.parse(newPayment.getDate(), formatter);
        LocalDate today = LocalDate.now();
        if(paymentDate.isBefore(today)) {
            throw new InvalidDateRangeException();
        }
    }

    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO newPaymentMethod) {
        return null;
    }

    @Override
    public PaymentMethodDTO findPaymentMethodById(Long id) {
        return null;
    }

    @Override
    public Boolean existPaymentMethodById(Long id) {
        return null;
    }

    @Override
    public List<PaymentMethodDTO> listPaymentMethod() {
        return null;
    }
}
