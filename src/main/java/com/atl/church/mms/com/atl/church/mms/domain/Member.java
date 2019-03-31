package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Member {

	public Member() {}
	public Member(Long id, String firstName, String middleName, String lastName, int age, String cellPhone, String email, String address1, String address2, String city, String state, String zipCode, boolean isActive, String password, Role role, String idCard) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.age = age;
		this.cellPhone = cellPhone;
		this.email = email;
		Address1 = address1;
		Address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.isActive = isActive;
		this.password = password;
		this.role = role;
		this.idCard = idCard;
	}

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private String cellPhone;
	private String email;
	private String Address1;
	private String Address2;
	private String city;
	private String state;
	private String zipCode;
	private boolean isActive;
	private String password;
	private Role role;
	private String idCard;

	public enum Role{
		User,Admin;
	}
}
