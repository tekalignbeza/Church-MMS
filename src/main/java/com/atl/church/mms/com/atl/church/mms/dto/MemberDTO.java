package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.dto.FamilyDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class MemberDTO {

	public MemberDTO() {}

	public MemberDTO(Long id, String firstName, String middleName, String lastName, Member.Gender gender, int yearOfBirth, boolean isFamilyHead, boolean isSpouse, FamilyDTO family, String cellPhone, String email, String idCard,UserDTO userDTO) {
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
		this.userDTO = userDTO;
	}

	private Long id;
	@NotNull(message = "Please provide First name ")
	private String firstName;
	private String middleName;
	@NotNull(message = "Please provide Last name ")
	private String lastName;
	@NotNull(message = "Please provide Gender ")
	Member.Gender gender;
	int yearOfBirth;
	private boolean isFamilyHead;
	private boolean isSpouse;
	private FamilyDTO family;
	@NotNull(message = "Please provide Cell phone ")
	private String cellPhone;
	private String email;
	private String idCard;
	private UserDTO userDTO;

}
