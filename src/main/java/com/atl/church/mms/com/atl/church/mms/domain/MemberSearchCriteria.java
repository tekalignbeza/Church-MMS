package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberSearchCriteria {

	public MemberSearchCriteria(String firstName, String middleName, String lastName, String cellPhone, String email) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.cellPhone = cellPhone;
		this.email = email;
	}

	public MemberSearchCriteria() {
	}

	private String firstName;
	private String middleName;
	private String lastName;
	private String cellPhone;
	private String email;
}
