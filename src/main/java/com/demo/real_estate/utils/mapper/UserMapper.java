package com.demo.real_estate.utils.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.real_estate.dto.RoleDTO;
import com.demo.real_estate.dto.UserCreationDTO;
import com.demo.real_estate.dto.UserDTO;
import com.demo.real_estate.model.user.Role;
import com.demo.real_estate.model.user.User;
public class UserMapper {
	private static UserMapper INSTANCE;
	
	@Autowired
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static UserMapper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserMapper();
		}
		return INSTANCE;
	}

	public User toEntity(UserCreationDTO dto) {
		User user = new User();
//		Set<Role> roles = new HashSet<>();
//		for (String roleName : dto.getRole()) {
//			RoleDTO roleDTO = new RoleDTO();
//			roleDTO.setName(roleName);
//			
//			Role role = RoleMapper.getInstance().toEntity(roleDTO);
//			roles.add(role);
//		}

		user = User.builder()
				.firstname(dto.getFirstname())
				.lastname(dto.getLastname())
				.email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword()))
				.username(dto.getUsername())
//				.roles(roles)
				.build();
				
		return user;
	}
	
	public UserDTO toDTO(User user) {
		UserDTO dto = UserDTO.builder()
						.id(user.getId())
						.username(user.getUsername())
						.enabled(user.isEnabled())
						.firstname(user.getFirstname())
						.lastname(user.getLastname())
						.email(user.getEmail())
						.role(user.getRoles().stream()
				                .map(role -> RoleMapper.getInstance().toDTO(role))
				                .collect(Collectors.toList()))
						.build();
		return dto;
	}
}
