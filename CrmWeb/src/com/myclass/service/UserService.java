package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	
	public UserService() {
		userRepository = new UserRepository();
	}
	
	public UserDto getById(int id) {
		UserDto dto = new UserDto();
		
		User entity = userRepository.findById(id);
		
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
		
		return dto;
	}
	
	public UserDto getByEmail(String email) {
		UserDto dto = new UserDto();
		
		User entity = userRepository.findByEmail(email);
		
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
		
		return dto;
	}
	
	public List<UserDto> getAll() {
		
		List<UserDto> dtos = new ArrayList<UserDto>();
		
		// Goi ham truy van du lieu
		List<User> entities = userRepository.findAll();
		// Tham chieu du lieu tu Entity -> Dto : Copy USB
		for (User entity: entities) {
			UserDto dto = new UserDto();
			dto.setId(entity.getId());
			dto.setUsername(entity.getUsername());
			dto.setPassword(entity.getPassword());
			dto.setEmail(entity.getEmail());
			dto.setAddress(entity.getAddress());
			dto.setFullname(entity.getFullname());
			dto.setPhone(entity.getPhone());
			dto.setRoleId(entity.getRoleId());
			
			dtos.add(dto);
		}
		// Tra ve Dto : Rut USB
		
		return dtos;
		//return userRepository.findAllJoin(); 
	}

	public int insert(UserDto userDto) {

		try {
			User entity = new User();
			
			String hashed = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
			
			entity.setUsername(userDto.getUsername());
			entity.setPassword(hashed);
			entity.setEmail(userDto.getEmail());
			entity.setAddress(userDto.getAddress());
			entity.setFullname(userDto.getFullname());
			entity.setPhone(userDto.getPhone());
			entity.setRoleId(userDto.getRoleId());

			return userRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int update(UserDto userDto) {
		
		try {
			User entity = userRepository.findById(userDto.getId());
			
			if (entity.equals(null)) return -1;
			
			entity.setId(userDto.getId());
			entity.setEmail(userDto.getEmail());
			entity.setFullname(userDto.getFullname());
			
			if (!userDto.getPassword().isEmpty()) {
				String hashed = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
				entity.setPassword(hashed);
			}
			
			return userRepository.edit(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
