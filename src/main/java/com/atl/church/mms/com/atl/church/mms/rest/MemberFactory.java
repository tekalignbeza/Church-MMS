package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberFactory {

	@Autowired
	FamilyFactory familyFactory;

	public Member2 toDomain(MemberDTO dto){
		return Member2.builder().gender(dto.getGender())
				.yearOfBirth(dto.getYearOfBirth())
				.cellPhone(dto.getCellPhone())
				.email(dto.getEmail()).firstName(dto.getFirstName())
				.id(dto.getId()).lastName(dto.getLastName()).middleName(dto.getMiddleName())
				.family(familyFactory.toDomain(dto.getFamily())).isFamilyHead(dto.isFamilyHead()).isSpouse(dto.isSpouse())
				.build();
	}

	public MemberDTO toDto(Member2 domain){
		return MemberDTO.builder().gender(domain.getGender()).yearOfBirth(domain.getYearOfBirth())
				.family(familyFactory.toDto(domain.getFamily())).isFamilyHead(domain.isFamilyHead())
				.isSpouse(domain.isSpouse())
				.cellPhone(domain.getCellPhone())
				.email(domain.getEmail()).firstName(domain.getFirstName())
				.idCard(domain.getIdCard())
				.id(domain.getId()).lastName(domain.getLastName()).middleName(domain.getMiddleName())
				.build();
	}

	public List<MemberDTO> toDtos(List<Member2> domains){
		return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
	}

	public List<Member2> toDomains(List<MemberDTO> dtos){
		return dtos.stream().map(d->toDomain(d)).collect(Collectors.toList());
	}
}
