package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Builder
@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String password;
	private Role role;
	public enum Role{
		User,Admin;
	}

	public User() {
	}

	public User(Long id, String email, String password, Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}
}
