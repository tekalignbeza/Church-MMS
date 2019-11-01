package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.domain.Family;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AttendanceDTO {

	private Long id;
	private Long memberId;
	private MeetingDTO meetingDTO;
	private FamilyDTO familyDTO;


	public AttendanceDTO(Long id,Long memberId, MeetingDTO meetingDTO, FamilyDTO familyDTO) {
		this.meetingDTO = meetingDTO;
		this.familyDTO = familyDTO;
		this.id = id;
		this.memberId = memberId;
	}
}
