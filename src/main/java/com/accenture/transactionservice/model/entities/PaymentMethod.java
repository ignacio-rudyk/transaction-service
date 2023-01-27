package com.accenture.transactionservice.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "bean_operation_payment")
    private String beanOperationPayment;

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

    public String getBeanOperationPayment() {
        return beanOperationPayment;
    }

    public void setBeanOperationPayment(String beanOperationPayment) {
        this.beanOperationPayment = beanOperationPayment;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beanOperationPayment='" + beanOperationPayment + '\'' +
                '}';
    }
}
