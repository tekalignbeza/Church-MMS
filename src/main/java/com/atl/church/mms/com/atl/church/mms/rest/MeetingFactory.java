package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingFactory {

	public Meeting toDomain(MeetingDTO dto){
		return Meeting.builder().Address1(dto.getAddress1())
				.Address2(dto.getAddress2()).agenda(dto.getAgenda())
				.cellPhone(dto.getCellPhone()).city(dto.getCity())
				.email(dto.getEmail()).dateTime(dto.getDateTime())
				.id(dto.getId()).title(dto.getTitle()).duration(dto.getDuration())
				.state(dto.getState()).zipCode(dto.getZipCode()).build();
	}

	public MeetingDTO toDto(Meeting domain){
		return MeetingDTO.builder().Address1(domain.getAddress1())
				.Address2(domain.getAddress2()).agenda(domain.getAgenda())
				.cellPhone(domain.getCellPhone()).city(domain.getCity())
				.email(domain.getEmail()).dateTime(domain.getDateTime()).duration(domain.getDuration())
				.state(domain.getState()).title(domain.getTitle()).zipCode(domain.getZipCode()).build();
	}

	public List<MeetingDTO> toDtos(List<Meeting> domains){
		return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
	}
}
