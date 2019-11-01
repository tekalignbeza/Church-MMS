package com.atl.church.mms.com.atl.church.mms.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class MeetingDTO {

	public MeetingDTO(){}

	public MeetingDTO(Long id,String cellPhone, String email, String address1, String address2, String city, String state, String zipCode, LocalDateTime dateTime, int duration, String agenda, String title) {
		this.id= id;
		this.cellPhone = cellPhone;
		this.email = email;
		Address1 = address1;
		Address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.dateTime = dateTime;
		this.duration = duration;
		this.agenda = agenda;
		this.title = title;
	}

	private Long id;
	private String cellPhone;
	private String email;
	@NotEmpty(message = "Please provide an Address")
	private String Address1;
	private String Address2;
	@NotEmpty(message = "Please provide City")
	private String city;
	@NotEmpty(message = "Please provide State")
	private String state;
	@NotEmpty(message = "Please provide Zip code")
	private String zipCode;
	@NotNull(message = "Please provide date time ")
	private LocalDateTime dateTime;
	private int duration;
	private String agenda;
	@NotEmpty(message = "Please provide a title")
	private String title;

}
