package com.accenture.transactionservice.service.implementation;

import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.MoneyOperationDTO;
import com.accenture.transactionservice.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Mapper mapper;

    private static String DOMAIN = "http://account-service";

    private static String ADD_AMOUNT_SERVICE = "/account/addAmount";

    private static String SUBTRACT_AMOUNT_SERVICE = "/account/subtractAmount";

    private static String GET_ACCOUNT_ID_BY_CBU = "/account/getAccountIdByCbu/";

    private static String GET_ACCOUNT_ID_BY_NUMBER_ACCOUNT = "/account/getAccountIdByNumberAccount/";

    @Override
    public MoneyOperationDTO addAmount(MoneyOperationDTO addingMoney) throws TransactionServiceException {
        try{
            String url = DOMAIN + ADD_AMOUNT_SERVICE;
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpEntity<MoneyOperationDTO> request = new HttpEntity<>(addingMoney, headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, ErrorResponse.class);
            return mapper.map(getResponse(responseEntity), MoneyOperationDTO.class);
        } catch (Throwable t) {
            throw t;
        }
    }
    @Override
    public MoneyOperationDTO subtractAmount(MoneyOperationDTO moneyTheft) throws TransactionServiceException {
        try {
            String url = DOMAIN + SUBTRACT_AMOUNT_SERVICE;
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpEntity<MoneyOperationDTO> request = new HttpEntity<>(moneyTheft, headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, ErrorResponse.class);
            return mapper.map(getResponse(responseEntity), MoneyOperationDTO.class);
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Long getAccountIdByCBU(String cbu) throws TransactionServiceException {
        try {
            String url = DOMAIN + GET_ACCOUNT_ID_BY_CBU + cbu;
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpEntity<String> request = new HttpEntity<>(cbu, headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, ErrorResponse.class);
            return new Long( (Integer) getResponse(responseEntity));
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Long getAccountIdByNumberAccount(String numberAccount) throws TransactionServiceException {
        try {
            String url = DOMAIN + GET_ACCOUNT_ID_BY_NUMBER_ACCOUNT + numberAccount;
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpEntity<String> request = new HttpEntity<>(numberAccount, headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, ErrorResponse.class);
            return new Long( (Integer) getResponse(responseEntity));
        } catch (Throwable t) {
            throw t;
        }
    }

    private Object getResponse(ResponseEntity<ErrorResponse> responseEntity) throws TransactionServiceException {
        ErrorResponse errorResponse = responseEntity.getBody();
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            if(errorResponse.getCode() == 0) {
                return errorResponse.getData();
            } else {
                throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
            }
        } else {
            throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
        }
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

}
