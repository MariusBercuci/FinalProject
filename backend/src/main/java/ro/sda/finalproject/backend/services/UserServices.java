package ro.sda.finalproject.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.UserDto;
import ro.sda.finalproject.backend.entity.User;
import ro.sda.finalproject.backend.mapper.UserMapper;
import ro.sda.finalproject.backend.repository.UserRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    public UserRepository userRepository;
    public UserMapper userMapper;

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    public UserDto save(UserDto request) {
        User user = userMapper.convertToEntity(request);
        userRepository.save(user);
        return userMapper.convertToDto(user);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found: " + id));
        return userMapper.convertToDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User currentUser = new User();
        currentUser.setId(id);
        currentUser.setEmail(userDto.getEmail());
        currentUser.setLastName(userDto.getLastName());
        currentUser.setPhone(userDto.getPhone());
        currentUser.setFirstName(userDto.getFirstName());
        currentUser.setPassword(userDto.getPassword());
        userRepository.save(currentUser);
        return userMapper.convertToDto(currentUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
