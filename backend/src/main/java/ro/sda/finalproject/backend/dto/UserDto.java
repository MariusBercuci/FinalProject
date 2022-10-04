package ro.sda.finalproject.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Getter
@Setter
@Service
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
