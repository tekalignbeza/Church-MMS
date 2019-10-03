package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
public class Member2 {

	public Member2() {}
	public Member2(Long id, String firstName, String middleName, String lastName, Gender gender, int yearOfBirth, String cellPhone, String email, boolean isFamilyHead, boolean isSpouse, boolean isActive, String password, Role role, String idCard, Family family) {
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
		this.password = password;
		this.role = role;
		this.idCard = idCard;
		this.family = family;
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
	private String password;
	private Role role;
	private String idCard;
	@ManyToOne
	@JoinColumn(name="family_id", nullable=false)
	private Family family;
	public enum Gender{
		Male,Female
	}
	public enum Role{
		User,Admin;
	}
}
