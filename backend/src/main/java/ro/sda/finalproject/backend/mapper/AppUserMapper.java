package ro.sda.finalproject.backend.mapper;


import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.entity.AppUser;

@Service
public class AppUserMapper implements Mapper<AppUser, AppUserDto> {
    @Override
    public AppUserDto convertToDto(AppUser entity) {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(entity.getId());
        appUserDto.setEmail(entity.getEmail());
        appUserDto.setFirstName(entity.getFirstName());
        appUserDto.setLastName(entity.getLastName());
        appUserDto.setPhone(entity.getPhone());
        appUserDto.setPassword(entity.getPassword());
        appUserDto.setRole(entity.getRole());
        appUserDto.setShoppingCart(entity.getShoppingCart());
        return appUserDto;
    }
    @Override
    public AppUser convertToEntity(AppUserDto dto) {
        AppUser appUser = new AppUser();
        appUser.setId(dto.getId());
        appUser.setPassword(dto.getPassword());
        appUser.setEmail(dto.getEmail());
        appUser.setFirstName(dto.getFirstName());
        appUser.setLastName(dto.getLastName());
        appUser.setPhone(dto.getPhone());
        appUser.setRole(dto.getRole());
        appUser.setShoppingCart(dto.getShoppingCart());
        return appUser;
    }
}
