package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    @ManyToOne
    private Family family;
    @ManyToOne
    private PaymentType paymentType;
    private TransactionMethod transactionMethod;
    private TransactionStatus status;
    private Double amount;
    private String note;
    private String reason;

    public Payment() {
    }

    public Payment(Long id,Long memberId, Family family, PaymentType paymentType, TransactionMethod transactionMethod, TransactionStatus status, Double amount, String note, String reason) {
        this.id = id;
        this.memberId = memberId;
        this.family = family;
        this.paymentType = paymentType;
        this.transactionMethod = transactionMethod;
        this.status = status;
        this.amount = amount;
        this.note = note;
        this.reason = reason;
    }
}
