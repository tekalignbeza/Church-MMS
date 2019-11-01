package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.User;
import com.atl.church.mms.com.atl.church.mms.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFactory {

	public User toDomain(UserDTO dto){
		return User.builder()
				.id(dto.getId())
				.role(dto.getRole())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.build();
	}

	public UserDTO toDto(User domain){
		return UserDTO.builder().id(domain.getId())
				.role(domain.getRole())
				.password(domain.getPassword())
				.email(domain.getEmail())
				.build();
	}

	public List<UserDTO> toDtos(List<User> domains){
		return domains.stream().map(d-> toDto(d)).collect(Collectors.toList());
	}

	public List<User> toDomains(List<UserDTO> dtos){
		return dtos.stream().map(d->toDomain(d)).collect(Collectors.toList());
	}
}
