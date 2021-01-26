package com.myclass.service;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

public class AuthService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public AuthService() {
		userRepository = new UserRepository();
		roleRepository = new RoleRepository();
	}
	
	
	public UserDto login(String email, String password) {
		
		//Truy van DB kiem tra email
		UserDto  dto = new UserDto();
		
		User entity = userRepository.findByEmail(email);
		
		//So sanh kiem tra mat khau
		if (!BCrypt.checkpw(password, entity.getPassword())) {
			return null;
		}
		
		dto = new UserDto();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setEmail(entity.getEmail());
		dto.setAddress(entity.getAddress());
		dto.setFullname(entity.getFullname());
		dto.setPhone(entity.getPhone());
		dto.setRoleId(entity.getRoleId());
		
		dto.setRoleDesc(dto.getRoleDesc());
		
		Role role = roleRepository.findById(entity.getRoleId());
		
		dto.setRoleName(role.getName());
		
		return dto;
		
		
	}
	
	
}
