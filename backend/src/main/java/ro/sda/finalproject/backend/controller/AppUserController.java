package ro.sda.finalproject.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.exception.EmailExistException;
import ro.sda.finalproject.backend.services.AppUserServices;


import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/users")
public class AppUserController {

    @Autowired
    private final AppUserServices appUserServices;

    @GetMapping()
    public ResponseEntity<List<AppUserDto>> getAllUsers() {
        List<AppUserDto> appUserDtoList = appUserServices.findAllUsers();
        return new ResponseEntity<>(appUserDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable("userId") Long id) {
        AppUserDto appUserDto = appUserServices.getUserById(id);
        return new ResponseEntity<>(appUserDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AppUserDto> createNewUser(@RequestBody @Valid AppUserDto appUserDto) throws EmailExistException {
        AppUserDto newAppUserDto = appUserServices.createNewUser(appUserDto);
        return new ResponseEntity<>(newAppUserDto,HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AppUserDto> updateUser(@PathVariable("userId")Long id, @RequestBody @Valid AppUserDto appUserDto) {
        AppUserDto newAppUserDto = appUserServices.updateUser(appUserDto);
        return new ResponseEntity<>(newAppUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId")Long id){
        appUserServices.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}