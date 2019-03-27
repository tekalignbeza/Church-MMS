package com.atl.church.mms.com.atl.church.mms.rest;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDTO {
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
}
