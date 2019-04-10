package com.atl.church.mms.com.atl.church.mms.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MeetingDTO {

	private Long id;
	private String cellPhone;
	private String email;
	private String Address1;
	private String Address2;
	private String city;
	private String state;
	private String zipCode;
	private LocalDateTime dateTime;
	private int duration;
	private String agenda;
	private String title;

}
