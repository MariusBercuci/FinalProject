package ro.sda.finalproject.backend.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.sda.finalproject.backend.entity.AppRole;
import ro.sda.finalproject.backend.entity.RoleName;
import ro.sda.finalproject.backend.mapper.ProductsMapper;
import ro.sda.finalproject.backend.repository.RoleRepository;
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
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
    @Bean
    CommandLineRunner run(AppUserServices appUserServices, RoleRepository roleRepository){

        return args -> {

            AppRole USER = new AppRole(null, RoleName.ROLE_USER);
            AppRole ADMIN = new AppRole(null, RoleName.ROLE_ADMIN);

            roleRepository.saveAll(List.of(ADMIN, USER));

            appUserServices.createNewUser("Croitoru","Mirel","croitoru_mirel@yahoo.com","0722469947","mirel123", RoleName.ROLE_USER);

        };
    }

}

