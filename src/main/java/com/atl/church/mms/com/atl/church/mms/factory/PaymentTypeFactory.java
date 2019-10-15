package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentTypeDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentTypeFactory {
    public PaymentType toDomain(PaymentTypeDTO dto){
        return PaymentType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription()).build();

    }

    public PaymentTypeDTO toDto(PaymentType domain){
        return PaymentTypeDTO.builder().id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription()).build();

    }

    public List<PaymentTypeDTO> toDtos(List<PaymentType> domains){
        return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
    }
}
