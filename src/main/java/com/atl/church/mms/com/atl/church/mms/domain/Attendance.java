package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
public class Attendance {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Meeting meeting;
	@ManyToOne
	private Family family;
	private long memberId;

	public Attendance() {}

	public Attendance(Long id,Meeting meeting, Family family, long memberId) {
		this.id = id;
		this.meeting = meeting;
		this.family = family;
		this.memberId = memberId;
	}
}
