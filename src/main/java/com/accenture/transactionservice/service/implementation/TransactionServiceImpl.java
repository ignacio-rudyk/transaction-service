package com.accenture.transactionservice.service.implementation;

import com.accenture.transactionservice.dao.TransactionDAO;
import com.accenture.transactionservice.model.dto.TransactionDTO;
import com.accenture.transactionservice.model.entities.Transaction;
import com.accenture.transactionservice.service.TransactionService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public TransactionDTO saveTransaction(TransactionDTO newTransaction) {
        if(newTransaction != null){
            Transaction transaction = mapper.map(newTransaction, Transaction.class);
            transaction = transactionDAO.save(transaction);
            return mapper.map(transaction, TransactionDTO.class);
        }
        return null;
    }

    @Override
    public TransactionDTO findById(Long id) {
        TransactionDTO ret = null;
        Optional<Transaction> result = transactionDAO.findById(id);
        if(!result.isEmpty()){
            ret = mapper.map(result.get(), TransactionDTO.class);
        }
        return ret;
    }

    @Override
    public Boolean existsById(Long id) {
        if(id != null) {
            return transactionDAO.existsById(id);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<TransactionDTO> list() {
        List<Transaction> list = (List<Transaction>) transactionDAO.findAll();
        return list.stream()
                .map(e -> mapper.map(e, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    private String createSequenceOfNumbers(Integer quantity){
        String cbu = "";
        for(int i = 0 ; i < quantity ; i++) {
            cbu += (int) (Math.random() * 9) + 1;
        }
        return cbu;
    }
}
