package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.dto.AttendanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttendaceFactory {

	@Autowired
	private FamilyFactory familyFactory;
	@Autowired
	private MeetingFactory meetingFactory;

	public AttendanceDTO toDto(Attendance domain){
		return AttendanceDTO.builder().id(domain.getId())
				.memberId(domain.getMemberId())
				.id(domain.getId())
				.familyDTO(familyFactory.toDto(domain.getFamily()))
				.meetingDTO(meetingFactory.toDto(domain.getMeeting()))
				.build();
	}

	public List<AttendanceDTO> toDtos(List<Attendance> domains){
		return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
	}
}
