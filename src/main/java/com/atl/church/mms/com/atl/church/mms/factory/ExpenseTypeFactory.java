package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.ExpenseType;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class ExpenseTypeFactory {

    public ExpenseType toDomain(ExpenseTypeDTO dto){
        return ExpenseType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public ExpenseTypeDTO  toDto(ExpenseType domain){
        return ExpenseTypeDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .build();
    }
}
