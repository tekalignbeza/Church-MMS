package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.dto.MemberDTO;
import com.atl.church.mms.com.atl.church.mms.factory.FamilyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberFactory {

	@Autowired
	FamilyFactory familyFactory;
	@Autowired
	UserFactory userFactory;

	public Member toDomain(MemberDTO dto){
		return Member.builder().gender(dto.getGender())
				.yearOfBirth(dto.getYearOfBirth())
				.cellPhone(dto.getCellPhone())
				.email(dto.getEmail()).firstName(dto.getFirstName())
				.id(dto.getId()).lastName(dto.getLastName()).middleName(dto.getMiddleName())
				.user(userFactory.toDomain(dto.getUserDTO()))
				.isFamilyHead(dto.isFamilyHead()).isSpouse(dto.isSpouse())
				.build();
	}

	public MemberDTO toDto(Member domain){
		return MemberDTO.builder().gender(domain.getGender()).yearOfBirth(domain.getYearOfBirth())
				.family(familyFactory.toDto(domain.getFamily())).isFamilyHead(domain.isFamilyHead())
				.isSpouse(domain.isSpouse())
				.cellPhone(domain.getCellPhone())
				.userDTO(userFactory.toDto(domain.getUser()))
				.email(domain.getEmail()).firstName(domain.getFirstName())
				.idCard(domain.getIdCard())
				.id(domain.getId()).lastName(domain.getLastName()).middleName(domain.getMiddleName())
				.build();
	}

	public List<MemberDTO> toDtos(List<Member> domains){
		if(domains!=null)
		return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
		else return new ArrayList<>();
	}

	public List<Member> toDomains(List<MemberDTO> dtos){
		if (dtos!=null)
		return dtos.stream().map(d->toDomain(d)).collect(Collectors.toList());
		else return new ArrayList<>();
	}
}
