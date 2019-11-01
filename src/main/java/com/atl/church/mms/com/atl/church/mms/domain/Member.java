package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "MemberTable")
public class Member {

	public Member() {}
	public Member(Long id, String firstName, String middleName, String lastName, Gender gender, int yearOfBirth, String cellPhone, String email, boolean isFamilyHead, boolean isSpouse, boolean isActive, String idCard, Family family,User user) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.yearOfBirth = yearOfBirth;
		this.cellPhone = cellPhone;
		this.email = email;
		this.isActive = isActive;
		this.isFamilyHead = false;
		this.isSpouse = isSpouse;
		this.idCard = idCard;
		this.family = family;
		this.user = user;
	}

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private  Gender gender;
	private  int yearOfBirth;
	private String cellPhone;
	private String email;
	private boolean isActive;
	private boolean isFamilyHead;
	private boolean isSpouse;
	private String idCard;
	@ManyToOne
	private Family family;
	@OneToOne
	private User user;
	public enum Gender{
		Male,Female
	}

}
