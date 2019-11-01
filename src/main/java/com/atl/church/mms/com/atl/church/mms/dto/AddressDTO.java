package com.atl.church.mms.com.atl.church.mms.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
public class AddressDTO {
	private Long id;
	private String streetAddress1;
	private String streetAddress2;
	private String city;
	private String state;
	private String zipCode;

	public AddressDTO(){ }
	public AddressDTO(Long id, String streetAddress1, String streetAddress2, String city, String state, String zipCode) {
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this. id = id;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
}
