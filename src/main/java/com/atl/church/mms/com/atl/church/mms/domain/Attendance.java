package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@Builder
public class Attendance {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Meeting meeting;
	@OneToOne
	private Member member;

	public Attendance() {
	}

	public Attendance(Long id,Meeting meeting, Member member) {
		this.id = id;
		this.meeting = meeting;
		this.member = member;
	}
}
