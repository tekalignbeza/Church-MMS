package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	private Long id;
	private String email;
	private String password;
	private User.Role role;

	public UserDTO() {}

	public UserDTO(long id, String email, String password, User.Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}
}
