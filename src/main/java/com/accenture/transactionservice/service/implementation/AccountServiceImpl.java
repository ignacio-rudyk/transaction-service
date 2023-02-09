package com.accenture.transactionservice.service.implementation;

import com.accenture.transactionservice.exception.TransactionServiceException;
import com.accenture.transactionservice.model.ErrorResponse;
import com.accenture.transactionservice.model.dto.SendingOfMoneyDTO;
import com.accenture.transactionservice.model.dto.WithdrawalOfMoneyDTO;
import com.accenture.transactionservice.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${account.service.server.port}")
    private String accountServicePort;

    @Autowired
    private Mapper mapper;

    private static String DOMAIN = "http://localhost:";

    private static String ADD_AMOUNT_SERVICE = "/account/addAmount";

    private static String SUBTRACT_AMOUNT_SERVICE = "/account/subtractAmount";

    private static String EXIST_BY_NUMBER_ACCOUNT_SERVICE = "/account/existAccountByNumberAccount/";

    private static String EXIST_BY_CBU_SERVICE = "/account/existAccountByCbu/";

    private static String NUMBER_ACCOUNT_PARAMETER = "numberAccount";

    private static String AMOUNT_PARAMETER = "amount";

    private static String CBU_DESTINATION_PARAMETER = "cbuDestination";

    @Override
    public SendingOfMoneyDTO addAmount(SendingOfMoneyDTO sendingOfMoney) throws TransactionServiceException {
        try{
            String url = DOMAIN + accountServicePort + ADD_AMOUNT_SERVICE;
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpEntity<SendingOfMoneyDTO> request = new HttpEntity<>(sendingOfMoney, headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, ErrorResponse.class);
            ErrorResponse errorResponse = responseEntity.getBody();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                if(errorResponse.getCode() == 0) {
                    return mapper.map(errorResponse.getData(), SendingOfMoneyDTO.class);
                } else {
                    throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
                }
            } else {
                throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
            }
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public WithdrawalOfMoneyDTO subtractAmount(WithdrawalOfMoneyDTO withdrawalOfMoney) throws TransactionServiceException {
        try {
            String url = DOMAIN + accountServicePort + SUBTRACT_AMOUNT_SERVICE;
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json");
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpEntity<WithdrawalOfMoneyDTO> request = new HttpEntity<>(withdrawalOfMoney, headers);
            ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, ErrorResponse.class);
            ErrorResponse errorResponse = responseEntity.getBody();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                if(errorResponse.getCode() == 0) {
                    return mapper.map(errorResponse.getData(), WithdrawalOfMoneyDTO.class);
                } else {
                    throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
                }
            } else {
                throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
            }
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Boolean existAccountByNumberAccount(String numberAccount) throws TransactionServiceException {
        try{
            String url = DOMAIN + accountServicePort + EXIST_BY_NUMBER_ACCOUNT_SERVICE + numberAccount;
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            ResponseEntity<ErrorResponse> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ErrorResponse.class);
            ErrorResponse errorResponse = responseEntity.getBody();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                if(errorResponse.getCode() == 0) {
                    return (Boolean) errorResponse.getData();
                } else {
                    throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
                }
            } else {
                throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
            }
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Boolean existAccountByCbu(String cbu) throws TransactionServiceException {
        try{
            String url = DOMAIN + accountServicePort + EXIST_BY_CBU_SERVICE + cbu;
            this.restTemplate.setMessageConverters(getJsonMessageConverters());
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            ResponseEntity<ErrorResponse> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ErrorResponse.class);
            ErrorResponse errorResponse = responseEntity.getBody();
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                if(errorResponse.getCode() == 0) {
                    return (Boolean) errorResponse.getData();
                } else {
                    throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
                }
            } else {
                throw new TransactionServiceException(errorResponse.getDesc(), errorResponse.getCode());
            }
        } catch (Throwable t) {
            throw t;
        }
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

}
