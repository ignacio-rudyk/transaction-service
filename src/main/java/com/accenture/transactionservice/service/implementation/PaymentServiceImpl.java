package com.accenture.transactionservice.service.implementation;

import com.accenture.transactionservice.dao.PaymentDAO;
import com.accenture.transactionservice.exception.TransactionInexistentException;
import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.exception.validation.FieldNullException;
import com.accenture.transactionservice.model.dto.*;
import com.accenture.transactionservice.model.entities.PaymentMethod;
import com.accenture.transactionservice.model.entities.Transaction;
import com.accenture.transactionservice.service.PaymentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO newPaymentMethod) {
        return null;
    }

    @Override
    public PaymentMethodDTO findPaymentMethodById(Long id) throws TransactionServiceException {
        if(id == null) {
            throw new FieldNullException();
        }
        Optional<PaymentMethod> result = paymentDAO.findById(id);
        if(!result.isEmpty()){
            return mapper.map(result.get(), PaymentMethodDTO.class);
        } else {
            throw new TransactionInexistentException();
        }
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
