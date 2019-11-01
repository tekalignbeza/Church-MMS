package com.atl.church.mms.com.atl.church.mms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class PaymentDTO {

    public PaymentDTO() {
    }

    @Builder
    public PaymentDTO(Long id, Long memberId, String note,FamilyDTO familyDTO, String  paymentMethod, Double amount, PaymentTypeDTO type, String status, String reason) {
        this.id = id;
        this.memberId = memberId;
        this.note = note;
        this.paymentMethod =  paymentMethod;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.familyDTO = familyDTO;
    }

    private Long id;
    private Long memberId;
    private String note;
    @NotEmpty(message = "please provide paymentMethod")
    private String paymentMethod;
    @NotNull(message = "please provide the amount")
    private Double amount;
    private String status;
    private PaymentTypeDTO type;
    private String reason;
    private FamilyDTO familyDTO;

}
