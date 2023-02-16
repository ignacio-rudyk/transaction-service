package com.accenture.transactionservice.model.dto;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionDTO implements Serializable {

    private Long id;

    private BigDecimal amount;

    private String datePayment;

    private Long paymentMethodId;

    private String cbuDestination;

    private String sourceAccountNumber;

    private Long sourceAccountId;

    private Long destinationAccountId;

    public TransactionDTO() {
        super();
    }

    public TransactionDTO(BigDecimal amount, String datePayment, Long paymentMethodId, String cbuDestination) {
        this.amount = amount;
        this.datePayment = datePayment;
        this.paymentMethodId = paymentMethodId;
        this.cbuDestination = cbuDestination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getCbuDestination() {
        return cbuDestination;
    }

    public void setCbuDestination(String cbuDestination) {
        this.cbuDestination = cbuDestination;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", datePayment='" + datePayment + '\'' +
                ", paymentMethodId=" + paymentMethodId +
                ", cbuDestination='" + cbuDestination + '\'' +
                ", sourceAccountNumber='" + sourceAccountNumber + '\'' +
                ", sourceAccountId=" + sourceAccountId +
                ", destinationAccountId=" + destinationAccountId +
                '}';
    }

}
