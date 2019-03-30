package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberSearchCriteria {

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String cellPhone;
	private String email;
}
