package ro.sda.finalproject.backend.mapper;

import ro.sda.javaro38.finalproject.dto.UserDto;
import ro.sda.javaro38.finalproject.entity.User;

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
