package com.accenture.transactionservice.model.dto;


import java.math.BigDecimal;
import java.util.Date;

public class PayDTO {

    private PaymentMethodDTO paymentMethod;

    private String numberAccount;

    private BigDecimal amount;

    private Date date;

    public PayDTO() {super();}

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PayDTO{" +
                "paymentMethod=" + paymentMethod +
                ", numberAccount='" + numberAccount + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

}
