package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentMethod;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentFactory {

    @Autowired
    private PaymentTypeFactory paymentTypeFactory;
    public Payment toDomain(PaymentDTO dto){
        return Payment.builder()
                .id(dto.getId())
                .memberId(dto.getMemberId())
                .paymentMethod(dto.getPaymentMethod() != null ? PaymentMethod.valueOf(dto.getPaymentMethod()) : null)
                .amount(dto.getAmount())
                .note(dto.getNote())
                .status(dto.getStatus() != null ? PaymentStatus.valueOf(dto.getStatus()): null)
                .type(paymentTypeFactory.toDomain(dto.getType()))
                .reason(dto.getReason())
                .build();
    }

    public PaymentDTO toDto(Payment domain){
        return PaymentDTO.builder()
                .id(domain.getId())
                .memberId(domain.getMemberId())
                .paymentMethod(domain.getPaymentMethod() != null ? domain.getPaymentMethod().name() : null)
                .amount(domain.getAmount())
                .note(domain.getNote())
                .type(paymentTypeFactory.toDto(domain.getType()))
                .status(domain.getStatus() != null ? domain.getStatus().name() : null)
                .reason(domain.getReason())
                .build();
    }

    public List<PaymentDTO> toDtos(List<Payment> domains){
        return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
    }
}
