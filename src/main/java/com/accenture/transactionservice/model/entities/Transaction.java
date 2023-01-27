package com.accenture.transactionservice.model.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss:SS")
    private Date createdAt;

    @Column(nullable = false)
    private Long paymentMethodId;

    @Column(nullable = false)
    private String cbuDestination;

    public Transaction() {
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
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", paymentMethodId=" + paymentMethodId +
                ", cbuDestination='" + cbuDestination + '\'' +
                '}';
    }
}
