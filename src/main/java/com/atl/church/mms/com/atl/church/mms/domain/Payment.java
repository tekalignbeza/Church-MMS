package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    @ManyToOne
    private PaymentType type;
    private TransactionMethod transactionMethod;
    private Double amount;
    private TransactionStatus status;
    private String note;
    private String reason;

    public Payment() {
    }

    public Payment(Long id, Long memberId, PaymentType type, TransactionMethod transactionMethod, Double amount, TransactionStatus status, String note, String reason) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.transactionMethod = transactionMethod;
        this.amount = amount;
        this.status = status;
        this.note = note;
        this.reason = reason;
    }
}
