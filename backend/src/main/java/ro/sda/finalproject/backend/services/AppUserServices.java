package ro.sda.finalproject.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.entity.AppUser;
import ro.sda.finalproject.backend.entity.AppUserDetails;
import ro.sda.finalproject.backend.exception.EmailExistException;
import ro.sda.finalproject.backend.exception.UserNotFoundException;
import ro.sda.finalproject.backend.mapper.AppUserMapper;
import ro.sda.finalproject.backend.repository.AppUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServices implements UserDetailsService {


    public AppUserRepository appUserRepository;
    public AppUserMapper appUserMapper;
    private BCryptPasswordEncoder passwordEncoder;

    public List<AppUserDto> findAllUsers() {
        return appUserRepository.findAll().stream().map(appUserMapper::convertToDto).collect(Collectors.toList());
    }


    public AppUserDto getUserById(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user by id " + id + " not found."));
        return appUserMapper.convertToDto(appUser);
    }

    public AppUserDto createNewUser(AppUserDto appUserDto) {
        AppUser newAppUser = appUserMapper.convertToEntity(appUserDto);
        Optional<AppUser> optionalUser = appUserRepository.findUserByEmail(newAppUser.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailExistException("The email " + newAppUser.getEmail() + " is already use");
        } else {
            appUserRepository.save(newAppUser);
        }
        return appUserMapper.convertToDto(newAppUser);
    }

    public AppUserDto updateUser(AppUserDto appUserDto) {
        AppUser currentAppUser = appUserMapper.convertToEntity(appUserDto);

        currentAppUser.setEmail(appUserDto.getEmail());
        currentAppUser.setLastName(appUserDto.getLastName());
        currentAppUser.setPhone(appUserDto.getPhone());
        currentAppUser.setFirstName(appUserDto.getFirstName());
        currentAppUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        appUserRepository.save(currentAppUser);
        return appUserMapper.convertToDto(currentAppUser);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = appUserRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("The user " + email + " not found.");
        } else {
            AppUser appUser = optionalUser.get();
            appUserRepository.save(appUser);
            return new AppUserDetails(appUser);
        }
    }
}