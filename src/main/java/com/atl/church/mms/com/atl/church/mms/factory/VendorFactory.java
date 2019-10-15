package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import com.atl.church.mms.com.atl.church.mms.dto.VendorDTO;
import com.atl.church.mms.com.atl.church.mms.factory.AddressFactory;
import com.atl.church.mms.com.atl.church.mms.factory.ExpenseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VendorFactory {


    @Autowired
    private ExpenseFactory expenseFactory;
    @Autowired
    private AddressFactory addressFactory;

    public Vendor toDomain(VendorDTO vendorDTO){

        return Vendor.builder()
                .id(vendorDTO.getId())
                .name(vendorDTO.getName())
                .address(addressFactory.toDomain(vendorDTO.getAddressDTO()))
                .expense(vendorDTO.getExpenseDTOS().stream().map(dto -> this.expenseFactory.toDomain(dto)).collect(Collectors.toList()))
                .build();

    }

    public VendorDTO toDto(Vendor vendor){
        return VendorDTO.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .addressDTO(addressFactory.toDto(vendor.getAddress()))
                .expenseDTOS(vendor.getExpense().stream().map(expense -> this.expenseFactory.toDto(expense)).collect(Collectors.toList()))
                .build();

    }
}
