package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Meeting {

	@Id
	@GeneratedValue
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

	public Meeting() {
	}

	public Meeting(Long id,String cellPhone, String email, String address1, String address2, String city, String state, String zipCode, LocalDateTime dateTime, int duration, String agenda, String title) {
		this.id = id;
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
}
