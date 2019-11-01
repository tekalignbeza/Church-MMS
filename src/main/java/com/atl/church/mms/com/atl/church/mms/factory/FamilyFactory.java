package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.dto.FamilyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FamilyFactory {

	@Autowired
    MemberFactory memberFactory;
	@Autowired
	PaymentFactory paymentFactory;
	@Autowired
	AttendaceFactory attendaceFactory;
	@Autowired
    AddressFactory addressFactory;

	public Family toDomain(FamilyDTO dto){
		return Family.builder().id(dto.getId()).address(addressFactory.toDomain(dto.getAddressDTO()))
				.name(dto.getName())
				.build();
	}

	public FamilyDTO toDto(Family domain){
		return FamilyDTO.builder().id(domain.getId()).addressDTO(addressFactory.toDto(domain.getAddress())).name(domain.getName())
				.build();
	}

}
