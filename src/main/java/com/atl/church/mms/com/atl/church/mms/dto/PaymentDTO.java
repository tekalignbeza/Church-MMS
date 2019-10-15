package com.atl.church.mms.com.atl.church.mms.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class PaymentDTO {

    public PaymentDTO() {
    }

    @Builder
    public PaymentDTO(Long id, Long memberId, String note, String  paymentMethod, Double amount, PaymentTypeDTO type, String status, String reason) {
        this.id = id;
        this.memberId = memberId;
        this.note = note;
        this.paymentMethod =  paymentMethod;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.reason = reason;
    }

    private Long id;
    private Long memberId;
    private String note;
    private String paymentMethod;
    private Double amount;
    private String status;
    private PaymentTypeDTO type;
    private String reason;


}
