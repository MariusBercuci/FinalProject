package ro.sda.finalproject.backend.mapper;

import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.RolesDto;
import ro.sda.finalproject.backend.entity.Roles;

@Service
public class RolesMapper implements Mapper<Roles, RolesDto>{
    @Override
    public RolesDto convertToDto(Roles entity) {
        RolesDto dto = new RolesDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        return dto;
    }

    @Override
    public Roles convertToEntity(RolesDto dto) {
        Roles entity = new Roles();
        entity.setRole(dto.getRole());
        entity.setId(dto.getId());
        return entity;
    }

}