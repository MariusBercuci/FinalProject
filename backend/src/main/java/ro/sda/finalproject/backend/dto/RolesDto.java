package ro.sda.finalproject.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ro.sda.finalproject.backend.entity.RolesName;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {

    private Long id;
    private RolesName roles;

}