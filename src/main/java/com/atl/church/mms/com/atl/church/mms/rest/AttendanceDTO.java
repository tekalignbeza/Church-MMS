package com.atl.church.mms.com.atl.church.mms.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AttendanceDTO {

	public AttendanceDTO(){}

	private Long meetingId;
	private Long memberId;

	public AttendanceDTO(Long meetingId, Long memberId) {
		this.meetingId = meetingId;
		this.memberId = memberId;
	}
}
