package ro.sda.finalproject.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String email;
    private String password;

}