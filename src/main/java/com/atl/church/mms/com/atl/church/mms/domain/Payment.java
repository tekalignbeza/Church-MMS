package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private String type;
    private PaymentMethod paymentMethod;
    private Double amount;
    private PaymentStatus status;
    private String note;
    private String reason;

    public Payment() {
    }

    public Payment(Long id, Long memberId, String type, PaymentMethod paymentMethod, Double amount, PaymentStatus status, String note, String reason) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.note = note;
        this.reason = reason;
    }
}
