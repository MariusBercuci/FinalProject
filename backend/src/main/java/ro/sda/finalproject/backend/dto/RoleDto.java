package ro.sda.finalproject.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ro.sda.finalproject.backend.entity.RoleName;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;
    private RoleName role;

}