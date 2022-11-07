package ro.sda.finalproject.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.dto.LoginDto;
import ro.sda.finalproject.backend.entity.AppUserDetails;
import ro.sda.finalproject.backend.entity.RoleName;
import ro.sda.finalproject.backend.exception.EmailExistException;
import ro.sda.finalproject.backend.services.AppUserServices;


import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/users")
public class AppUserController {

    private final AppUserServices appUserServices;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/all")
    public ResponseEntity<List<AppUserDto>> getAllUsers() {
        List<AppUserDto> appUserDtoList = appUserServices.findAllUsers();
        return new ResponseEntity<>(appUserDtoList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable("id") Long id) {
        AppUserDto appUserDto = appUserServices.getUserById(id);
        return new ResponseEntity<>(appUserDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Valid
    public ResponseEntity<AppUserDto> createNewUser(@RequestParam("firstName") String firstName,
                                                    @RequestParam("lastName") String lastName,
                                                    @RequestParam("email") String email,
                                                    @RequestParam("phone") String phone,
                                                    @RequestParam("password") String password,
                                                    @RequestParam("role") String role) throws EmailExistException {
        AppUserDto newAppUserDto = appUserServices.createNewUser(firstName, lastName, email, phone, password,RoleName.valueOf(role));
        return new ResponseEntity<>(newAppUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUserDto> registerNewUser(@RequestBody @Valid AppUserDto appUserDto){
        AppUserDto newAppUserDto = appUserServices.registerNewUser(appUserDto);
        return new ResponseEntity<>(newAppUserDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AppUserDto> loginUser(@RequestBody LoginDto loginDto) {
        authenticate(loginDto.getEmail(), loginDto.getPassword());
        appUserServices.getUserDetails(loginDto.getEmail());
        AppUserDto loginUser = appUserServices.findUserByEmail(loginDto.getEmail());
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Valid
    public ResponseEntity<AppUserDto> updateUser(@RequestParam("firstName") String firstName,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("email") String newEmail,
                                                 @RequestParam("currentEmail") String currentEmail,
                                                 @RequestParam("phone") String phone,
                                                 @RequestParam("password") String password) throws EmailExistException {
        AppUserDto newAppUserDto = appUserServices.updateUser(firstName, lastName, phone, password, newEmail, currentEmail);
        return new ResponseEntity<>(newAppUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        appUserServices.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authToken);
    }
}