package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Address;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {

	public Address toDomain(AddressDTO dto){
		return Address.builder().streetAddress1(dto.getStreetAddress1()).streetAddress2(dto.getStreetAddress2())
				.city(dto.getCity()).state(dto.getState()).zipCode(dto.getZipCode()).build();
	}

	public AddressDTO toDto(Address domain){
		return AddressDTO.builder().streetAddress1(domain.getStreetAddress1()).streetAddress2(domain.getStreetAddress2())
				.city(domain.getCity()).state(domain.getState()).zipCode(domain.getZipCode()).build();
	}

}
