package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.dto.AddressDTO;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class VendorDTO {

    private Long id;
    private String name;
    private AddressDTO addressDTO;
    private List<ExpenseDTO> expenseDTOS;


    @Builder
    public VendorDTO(Long id, String name, AddressDTO addressDTO, List<ExpenseDTO> expenseDTOS) {
        this.id = id;
        this.name = name;
        this.addressDTO=addressDTO;
        this.expenseDTOS = expenseDTOS;
    }
}
