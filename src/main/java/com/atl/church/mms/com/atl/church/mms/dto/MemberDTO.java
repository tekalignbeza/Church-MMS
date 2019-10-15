package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import com.atl.church.mms.com.atl.church.mms.dto.FamilyDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDTO {

	public MemberDTO() {}

	public MemberDTO(Long id, String firstName, String middleName, String lastName, Member2.Gender gender, int yearOfBirth, boolean isFamilyHead, boolean isSpouse, FamilyDTO family, String cellPhone, String email, String idCard) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.yearOfBirth = yearOfBirth;
		this.isFamilyHead = isFamilyHead;
		this.isSpouse = isSpouse;
		this.family = family;
		this.cellPhone = cellPhone;
		this.email = email;
		this.idCard = idCard;
	}

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	Member2.Gender gender;
	int yearOfBirth;
	private boolean isFamilyHead;
	private boolean isSpouse;
	private FamilyDTO family;
	private String cellPhone;
	private String email;
	private String idCard;

}
