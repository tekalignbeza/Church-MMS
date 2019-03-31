package com.atl.church.mms.com.atl.church.mms.rest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class MemberDTO {

	public MemberDTO() {}

	public MemberDTO(Long id, String firstName, String middleName, String lastName, int age, String cellPhone, String email, String address1, String address2, String city, String state, String zipCode, String idCard) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.age = age;
		this.cellPhone = cellPhone;
		this.email = email;
		Address1 = address1;
		Address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.idCard = idCard;
	}

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private String cellPhone;
	private String email;
	private String Address1;
	private String Address2;
	private String city;
	private String state;
	private String zipCode;
	private String idCard;

}
