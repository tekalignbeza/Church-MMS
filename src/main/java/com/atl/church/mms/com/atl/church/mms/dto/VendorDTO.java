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
    private UserDTO userDTO;

    public VendorDTO(){

    }

    @Builder
    public VendorDTO(Long id, String name, AddressDTO addressDTO, UserDTO userDTO) {
        this.id = id;
        this.name = name;
        this.addressDTO=addressDTO;
        this.userDTO = userDTO;
    }
}
