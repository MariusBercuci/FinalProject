package ro.sda.finalproject.backend.mapper;

import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.RoleDto;
import ro.sda.finalproject.backend.entity.AppRole;

@Service
public class RoleMapper implements Mapper<AppRole, RoleDto>{
    @Override
    public RoleDto convertToDto(AppRole entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        return dto;
    }

    @Override
    public AppRole convertToEntity(RoleDto dto) {
        AppRole entity = new AppRole();
        entity.setRole(dto.getRole());
        entity.setId(dto.getId());
        return entity;
    }

}