package ro.sda.finalproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.javaro38.finalproject.dto.UserDto;
import ro.sda.javaro38.finalproject.entity.User;
import ro.sda.javaro38.finalproject.repository.UserRepository;
import ro.sda.javaro38.finalproject.services.UserServices;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllClients() {
        List<UserDto> userDtoList = userServices.findAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long id) {
        UserDto userDto = userServices.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping()
    public UserDto addNewClient(@RequestBody UserDto request) {
        return userServices.save(request);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long id, @RequestBody UserDto userDto) {
        UserDto newUserDto = userServices.updateUser(id, userDto);
        return new ResponseEntity<>(newUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId")Long id){
        userServices.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable < Client > getAllUsers() {
//        return clientRepository.findAll();
//    }

}