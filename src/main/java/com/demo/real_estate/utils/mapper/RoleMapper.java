package com.demo.real_estate.utils.mapper;

import com.demo.real_estate.dto.RoleDTO;
import com.demo.real_estate.model.user.Role;

public class RoleMapper {

    private static RoleMapper INSTANCE;

    public static RoleMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleMapper();
        }

        return INSTANCE;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

    public RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setName(role.getName());
        dto.setId(role.getId());
        return dto;
    }
}