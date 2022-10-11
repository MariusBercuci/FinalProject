package ro.sda.finalproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.finalproject.backend.dto.UserDto;
import ro.sda.finalproject.backend.exception.EmailExistException;
import ro.sda.finalproject.backend.services.UserServices;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userServices.findAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto userDto = userServices.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid UserDto userDto) throws EmailExistException {
        UserDto newUserDto = userServices.createNewUser(userDto);
        return new ResponseEntity<>(newUserDto,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {
        UserDto newUserDto = userServices.updateUser(userDto);
        return new ResponseEntity<>(newUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Long id){
        userServices.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}