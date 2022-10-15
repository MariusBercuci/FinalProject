package ro.sda.finalproject.backend.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.entity.AppRole;
import ro.sda.finalproject.backend.entity.AppUser;
import ro.sda.finalproject.backend.entity.RoleName;
import ro.sda.finalproject.backend.mapper.AppUserMapper;
import ro.sda.finalproject.backend.mapper.ProductsMapper;
import ro.sda.finalproject.backend.repository.AppUserRepository;
import ro.sda.finalproject.backend.repository.RoleRepository;
import ro.sda.finalproject.backend.services.AppUserServices;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@AllArgsConstructor
public class BeanConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public ProductsMapper createProductsMapper() {
        return new ProductsMapper();
    }

    @Bean
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    CommandLineRunner run(AppUserServices appUserServices, RoleRepository roleRepository, AppUserRepository appUserRepository, AppUserMapper appUserMapper) {

        return args -> {

            AppRole USER = new AppRole(null, RoleName.ROLE_USER);
            AppRole ADMIN = new AppRole(null, RoleName.ROLE_ADMIN);
            AppUserDto newAppUser;

            roleRepository.saveAll(List.of(ADMIN, USER));
            newAppUser = appUserServices.createNewUser("Croitoru", "Mirel", "croitoru_mirel@yahoo.com", "0722469947", "mirel123", RoleName.ROLE_ADMIN, true , true);
            appUserRepository.save(appUserMapper.convertToEntity(newAppUser));
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);


    }
}

