package com.atl.church.mms.com.atl.church.mms.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FamilyDTO {

	long id;
	String name;
	AddressDTO addressDTO;
	List<MemberDTO> memberDTOList;

	public FamilyDTO(){}

	public FamilyDTO(long id, String name, AddressDTO addressDTO, List<MemberDTO> memberDTOList) {
		this.id = id;
		this.name = name;
		this.addressDTO = addressDTO;
		this.memberDTOList = memberDTOList;
	}
}
