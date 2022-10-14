package ro.sda.finalproject.backend.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.sda.finalproject.backend.entity.Roles;
import ro.sda.finalproject.backend.entity.RolesName;
import ro.sda.finalproject.backend.mapper.ProductsMapper;
import ro.sda.finalproject.backend.repository.RolesRepository;
import ro.sda.finalproject.backend.services.AppUserServices;


import java.util.List;

@Configuration
@AllArgsConstructor
public class BeanConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public ProductsMapper createProductsMapper(){
        return new ProductsMapper();
}
    @Bean
    CommandLineRunner run(AppUserServices appUserServices, RolesRepository rolesRepository){

        return args -> {

            Roles USER = new Roles(null, RolesName.ROLE_USER);
            Roles ADMIN = new Roles(null, RolesName.ROLE_ADMIN);

            rolesRepository.saveAll(List.of(ADMIN, USER));

            appUserServices.createNewUser("Croitoru","Mirel","croitoru_mirel@yahoo.com","0722469947","mirel123",RolesName.ROLE_USER);

        };
    }

}

