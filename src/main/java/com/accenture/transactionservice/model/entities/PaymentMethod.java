package com.accenture.transactionservice.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "bean_payment_operation")
    private String beanPaymentOperation;

    public PaymentMethod() {
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
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beanOperationPayment='" + beanPaymentOperation + '\'' +
                '}';
    }
}
