package ro.sda.finalproject.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ro.sda.finalproject.backend.services.AppUserServices;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AppUserServices appUserServices;
    private final PasswordEncoder passwordEncoder;



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserServices);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         //http.csrf().disable();
        // http.cors().disable();
         //http.authorizeRequests()
                 //.antMatchers(HttpMethod.DELETE,"/api/users").hasRole("ADMIN")

                 //.anyRequest().authenticated();

         http.httpBasic()
                .and()
                 .formLogin();

         return http.build();
    }
}
