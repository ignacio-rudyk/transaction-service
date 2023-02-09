package com.accenture.transactionservice.model.dto;

public class PaymentMethodDTO {

    private Long id;

    private String name;

    private String beanPaymentOperation;

    public PaymentMethodDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeanPaymentOperation() {
        return beanPaymentOperation;
    }

    public void setBeanPaymentOperation(String beanPaymentOperation) {
        this.beanPaymentOperation = beanPaymentOperation;
    }

    @Override
    public String toString() {
        return "PaymentMethodDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beanPaymentOperation='" + beanPaymentOperation + '\'' +
                '}';
    }

}
