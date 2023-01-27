package com.accenture.transactionservice.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal amount;

    private Date createdAt;

    private Long paymentMethodId;

    private String cbuDestination;

    public TransactionDTO() {
        super();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", paymentMethodId=" + paymentMethodId +
                ", cbu='" + cbuDestination + '\'' +
                '}';
    }

}
