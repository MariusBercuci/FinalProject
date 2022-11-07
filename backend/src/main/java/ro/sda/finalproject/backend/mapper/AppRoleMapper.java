package ro.sda.finalproject.backend.mapper;

import org.springframework.stereotype.Service;

import ro.sda.finalproject.backend.dto.AppRoleDto;
import ro.sda.finalproject.backend.entity.AppRole;

@Service
public class AppRoleMapper implements Mapper<AppRole, AppRoleDto>{
    @Override
    public AppRoleDto convertToDto(AppRole entity) {
        AppRoleDto dto = new AppRoleDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        return dto;
    }

    @Override
    public AppRole convertToEntity(AppRoleDto dto) {
        AppRole entity = new AppRole();
        entity.setRole(dto.getRole());
        entity.setId(dto.getId());
        return entity;
    }

}