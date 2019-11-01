package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FamilyDTO {

	Long id;
	String name;
	AddressDTO addressDTO;
	List<MemberDTO> memberDTOList;
	List<PaymentDTO> paymentDTOList;
	List<AttendanceDTO> attendanceDTOList;

	public FamilyDTO(){}

	public FamilyDTO(Long id, String name, AddressDTO addressDTO, List<MemberDTO> memberDTOList,List<PaymentDTO> paymentDTOList,List<AttendanceDTO> attendanceDTOList) {
		this.id = id;
		this.name = name;
		this.addressDTO = addressDTO;
		this.memberDTOList = memberDTOList;
		this.paymentDTOList =paymentDTOList;
		this.attendanceDTOList = attendanceDTOList;
	}
}
