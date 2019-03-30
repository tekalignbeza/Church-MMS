package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberFactory {

	public Member toDomain(MemberDTO dto){
		return Member.builder().Address1(dto.getAddress1())
				.Address2(dto.getAddress2()).age(dto.getAge())
				.cellPhone(dto.getCellPhone()).city(dto.getCity())
				.email(dto.getEmail()).firstName(dto.getFirstName())
				.id(dto.getId()).lastName(dto.getLastName()).middleName(dto.getMiddleName())
				.state(dto.getState()).zipCode(dto.getZipCode()).build();
	}

	public MemberDTO toDto(Member domain){
		return MemberDTO.builder().Address1(domain.getAddress1())
				.Address2(domain.getAddress2()).age(domain.getAge())
				.cellPhone(domain.getCellPhone()).city(domain.getCity())
				.email(domain.getEmail()).firstName(domain.getFirstName())
				.idCard(domain.getIdCard())
				.id(domain.getId()).lastName(domain.getLastName()).middleName(domain.getMiddleName())
				.state(domain.getState()).zipCode(domain.getZipCode()).build();
	}

	public List<MemberDTO> toDtos(List<Member> domains){
		return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
	}
}
