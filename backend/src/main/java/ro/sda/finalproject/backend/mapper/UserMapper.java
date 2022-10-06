package ro.sda.finalproject.backend.mapper;


import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.UserDto;
import ro.sda.finalproject.backend.entity.User;
@Service
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto convertToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setPhone(entity.getPhone());
        userDto.setPassword(entity.getPassword());
        return userDto;
    }
    @Override
    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        return user;
    }
}
