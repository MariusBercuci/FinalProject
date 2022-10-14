package ro.sda.finalproject.backend.services;

import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.entity.AppUser;
import ro.sda.finalproject.backend.entity.AppUserDetails;
import ro.sda.finalproject.backend.entity.Roles;
import ro.sda.finalproject.backend.entity.RolesName;
import ro.sda.finalproject.backend.exception.EmailExistException;
import ro.sda.finalproject.backend.exception.UserNotFoundException;
import ro.sda.finalproject.backend.mapper.AppUserMapper;
import ro.sda.finalproject.backend.repository.AppUserRepository;
import ro.sda.finalproject.backend.repository.RolesRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static ro.sda.finalproject.backend.constants.UserImplConstant.EMAIL_ALREADY_EXISTS;
import static ro.sda.finalproject.backend.constants.UserImplConstant.NO_USER_FOUND_BY_EMAIL;

@Service
@AllArgsConstructor
public class AppUserServices implements UserDetailsService {


    private  AppUserRepository appUserRepository;
    private AppUserMapper appUserMapper;
    private BCryptPasswordEncoder passwordEncoder;

    private RolesRepository rolesRepository;

    public List<AppUserDto> findAllUsers() {
        return appUserRepository.findAll().stream().map(appUserMapper::convertToDto).collect(Collectors.toList());
    }


    public AppUserDto getUserById(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user by id " + id + " not found."));
        return appUserMapper.convertToDto(appUser);
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
    public AppUserDetails getUserDetails(String email) {
        AppUser loginUser = appUserRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("No user with email " + email + " was found")));
        return new AppUserDetails(loginUser);
    }
    public AppUserDto findUserByEmail(String email) {
        AppUser appUser = appUserRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("No user with email " + email + " was found")));
        return appUserMapper.convertToDto(appUser);
}

    public AppUserDto createNewUser(String firstName, String lastName, String email, String phone, String password, RolesName roles) {
        validateNewUserEmail(EMPTY,email);
        AppUser appUser = new AppUser();
        Roles userRoles = rolesRepository.findByRole(roles);

        appUser.setEmail(email);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setPhone(phone);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(Set.of(userRoles));
        appUserRepository.save(appUser);
        return appUserMapper.convertToDto(appUser);
    }
    private AppUser validateNewUserEmail(String currentEmail, String newEmail) throws UserNotFoundException, EmailExistException {

        Optional<AppUser> userWithNewEmail = appUserRepository.findUserByEmail(newEmail);

        if (StringUtils.isNotBlank(currentEmail)) {
            Optional<AppUser> currentUser = appUserRepository.findUserByEmail(currentEmail);

            if (currentUser.isEmpty()) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_EMAIL + currentEmail);
            }
            if (userWithNewEmail.isPresent() && !currentUser.get().getId().equals(userWithNewEmail.get().getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser.get();
        } else {
            if (userWithNewEmail.isPresent()) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }
}