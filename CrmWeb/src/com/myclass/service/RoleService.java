package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;

public class RoleService {
	
	private RoleRepository roleRepository;
	
	
	
	public RoleService() {
		roleRepository = new RoleRepository();
	}



	public List<RoleDto> getAll() {
		
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		
		// Goi ham truy van du lieu
		List<Role> entities = roleRepository.findAll();
		// Tham chieu du lieu tu Entity -> Dto : Copy USB
		for (Role entity: entities) {
			RoleDto dto = new RoleDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDesc(entity.getDescription());
			
			dtos.add(dto);
		}
		// Tra ve Dto : Rut USB
		return dtos;
	}
	
	public RoleDto getById(int id) {
		RoleDto dto = new RoleDto();
		
		Role entity = roleRepository.findById(id);
		
		dto = new RoleDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDesc(entity.getDescription());
		
		return dto;
	}
	
	public int insert(RoleDto roleDto) {
		Role entity = new Role();
		
		entity.setName(roleDto.getName());
		entity.setDescription(roleDto.getDesc());
		
		return roleRepository.save(entity);
	}
	
	public int update(RoleDto roleDto) {
		Role entity = roleRepository.findById(roleDto.getId());
		
		if (entity.equals(null)) return -1;
		
		entity.setId(roleDto.getId());
		entity.setName(roleDto.getName());
		entity.setDescription(roleDto.getDesc());
		
		return roleRepository.edit(entity);
	}
	
}
