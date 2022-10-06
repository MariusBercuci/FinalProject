package ro.sda.finalproject.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.UserDto;
import ro.sda.finalproject.backend.entity.User;
import ro.sda.finalproject.backend.exception.EmailExistException;
import ro.sda.finalproject.backend.exception.UserNotFoundException;
import ro.sda.finalproject.backend.mapper.UserMapper;
import ro.sda.finalproject.backend.repository.UserRepository;


import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServices implements UserDetailsService {


    public UserRepository userRepository;
    public UserMapper userMapper;

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }


    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user by id " + id + " not found."));
        return userMapper.convertToDto(user);
    }

    public UserDto createNewUser(UserDto userDto){
        User newUser = userMapper.convertToEntity(userDto);
        Optional<User> optionalUser = userRepository.findUserByEmail(newUser.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailExistException("The email " + newUser.getEmail() + " is already use");
        }else {
            userRepository.save(newUser);
        }
        return userMapper.convertToDto(newUser);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User currentUser = userMapper.convertToEntity(userDto);

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
